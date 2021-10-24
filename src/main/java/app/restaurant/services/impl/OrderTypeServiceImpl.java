package app.restaurant.services.impl;

import app.restaurant.models.bindings.OrderTypeAddBindingModel;
import app.restaurant.models.bindings.OrderTypeEditBindingModel;
import app.restaurant.models.dtos.OrderTypeViewDto;
import app.restaurant.models.dtos.UserViewDto;
import app.restaurant.models.entities.OrderType;
import app.restaurant.repositories.OrderTypeRepository;
import app.restaurant.services.OrderTypeService;
import app.restaurant.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderTypeServiceImpl implements OrderTypeService {
    private final OrderTypeRepository orderTypeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public OrderTypeServiceImpl(OrderTypeRepository orderTypeRepository, ModelMapper modelMapper, UserService userService) {
        this.orderTypeRepository = orderTypeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
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
}
