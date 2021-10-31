package app.restaurant.services.impl;

import app.restaurant.models.bindings.CustomerAddOrderBindingModel;
import app.restaurant.models.bindings.OrderTypeAddBindingModel;
import app.restaurant.models.bindings.OrderTypeEditBindingModel;
import app.restaurant.models.bindings.WaiterAddOrderBindingModel;
import app.restaurant.models.dtos.*;
import app.restaurant.models.entities.Order;
import app.restaurant.models.entities.OrderType;
import app.restaurant.models.entities.User;
import app.restaurant.repositories.OrderTypeRepository;
import app.restaurant.services.MealPreparationService;
import app.restaurant.services.OrderService;
import app.restaurant.services.OrderTypeService;
import app.restaurant.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderTypeServiceImpl implements OrderTypeService {
    private final OrderTypeRepository orderTypeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final OrderService orderService;
    private final MealPreparationService mealPreparationService;

    public OrderTypeServiceImpl(OrderTypeRepository orderTypeRepository, ModelMapper modelMapper, UserService userService,
                                OrderService orderService, MealPreparationService mealPreparationService) {
        this.orderTypeRepository = orderTypeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.orderService = orderService;
        this.mealPreparationService = mealPreparationService;
    }

    @Override
    public boolean addOrderType(OrderTypeAddBindingModel orderTypeAddBindingModel) {
        OrderType tryToFind = orderTypeRepository.findByName(orderTypeAddBindingModel.getName()).orElse(null);
        if (tryToFind != null) {
            return true;
        }
        OrderType toAdd = modelMapper.map(orderTypeAddBindingModel, OrderType.class);
        toAdd.setWaiter(userService.getUserByUsername(orderTypeAddBindingModel.getWaiterName()));
        orderTypeRepository.save(toAdd);
        return false;
    }

    @Override
    public List<OrderTypeViewDto> getAllTables() {
        List<OrderTypeViewDto> result = new ArrayList<>();
        orderTypeRepository.findAllTablesSortedByName().stream().forEach(t -> {
            OrderTypeViewDto current = modelMapper.map(t, OrderTypeViewDto.class);
            current.setWaiter(modelMapper.map(t.getWaiter(), UserViewDto.class));
            result.add(current);
        });
        return result;
    }

    @Override
    public void deleteTable(Long id) {
        orderTypeRepository.deleteById(id);
    }

    @Override
    public OrderTypeViewDto getTableById(Long id) {
        return orderTypeRepository.findById(id).map(t -> modelMapper.map(t, OrderTypeViewDto.class)).orElse(null);
    }

    @Override
    public void editTable(OrderTypeEditBindingModel orderTypeEditBindingModel) {
        OrderType toEdit = orderTypeRepository.findById(orderTypeEditBindingModel.getId()).orElse(null);
        toEdit.setActive(orderTypeEditBindingModel.isActive());
        toEdit.setWaiter(userService.getUserByUsername(orderTypeEditBindingModel.getWaiterName()));
        orderTypeRepository.save(toEdit);
    }

    @Override
    public OrderType getTableByName(String name) {
        return orderTypeRepository.findByName(name).orElse(null);
    }

    @Override
    public void initTables() {
        if (orderTypeRepository.count() == 0) {
        OrderType online = new OrderType();
        online.setActive(true);
        online.setName("online");
        online.setWaiter(userService.getUserByUsername("waiter1"));
        orderTypeRepository.save(online);
        OrderType t1 = new OrderType();
        t1.setActive(true);
        t1.setName("t1");
        t1.setWaiter(userService.getUserByUsername("waiter1"));
        orderTypeRepository.save(t1);
        OrderType t2 = new OrderType();
        t2.setActive(true);
        t2.setName("t2");
        t2.setWaiter(userService.getUserByUsername("waiter2"));
        orderTypeRepository.save(t2);
        }
    }

    @Override
    public List<OrderTypeWaiterViewDto> getTablesByWaiter(Long waiterId) {
        List<OrderTypeWaiterViewDto> result = new ArrayList<>();
        orderTypeRepository.findTablesByWaiterId(waiterId).stream().forEach(ot -> {
            if (!ot.getName().equals("online")) {
            OrderTypeWaiterViewDto current = modelMapper.map(ot, OrderTypeWaiterViewDto.class);
            Order isAny = orderService.getOpenOrderByTableId(ot.getId());
            if (isAny == null) {
                current.setFree(true);
                current.setSum(0.0);
                current.setListMeals(new ArrayList<>());
            }
            else {
                current.setFree(false);
                current.setSum(mealPreparationService.getSumOfOrderId(isAny.getId()));
                current.setListMeals(mealPreparationService.getMealPreparationsbyOrderId(isAny.getId()));
            }
            result.add(current);
            }
        });
        return result;
    }

    @Override
    public void createNewOrderFromWaiters(WaiterAddOrderBindingModel[] mealsArr) {
        OrderType table = orderTypeRepository.findById(mealsArr[0].getTableId()).orElse(null);
        Order order = orderService.createNewOrderFromWaiter(table);
        Arrays.stream(mealsArr).forEach(mi -> {
            mealPreparationService.createNewMealPreparationFromWaiter(order, mi.getMealId(), mi.getQuantity());
        });
    }

    @Override
    public void closeOrderOnTable(String tableName) {
        Order toClose = orderService.getOpenOrderByTableName(tableName);
        toClose.setOpen(false);
        orderService.saveOrder(toClose);
    }

    @Override
    public List<WaiterMealsInOrder> getAllMealsInOrderForTableId(Long tableId) {
        Order toEdit = orderService.getOpenOrderByTableId(tableId);
        List<MealPreparationWaiterViewDto> listMeals = mealPreparationService.getMealPreparationsbyOrderId(toEdit.getId());
        return listMeals.stream().map(mp -> modelMapper.map(mp, WaiterMealsInOrder.class)).collect(Collectors.toList());
    }

    @Override
    public void editOrder(Long tableId, WaiterAddOrderBindingModel[] mealsArr) {
        Order orderToEdit = orderService.getOpenOrderByTableId(tableId);
        mealPreparationService.getMealPreparationsbyOrderId(orderToEdit.getId()).stream().forEach(mp -> {
            mealPreparationService.deleteMealPreparationById(mp.getId());
        });
        orderService.deleteOrderById(orderToEdit.getId());
        this.createNewOrderFromWaiters(mealsArr);
    }

    @Override
    public Long getTableIdByName(String tableName) {
        OrderType result = orderTypeRepository.findByName(tableName).orElse(null);
        return result.getId();
    }

    @Override
    public void createNewOrderFromCustomer(CustomerAddOrderBindingModel[] mealsArr) {
        OrderType online = orderTypeRepository.findByName("online").orElse(null);
        User onlineUser = userService.getOnlineUserById(mealsArr[0].getCustomerId());
    }
}
