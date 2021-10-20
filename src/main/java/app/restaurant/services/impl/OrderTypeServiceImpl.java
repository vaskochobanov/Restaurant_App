package app.restaurant.services.impl;

import app.restaurant.models.bindings.OrderTypeAddBindingModel;
import app.restaurant.models.dtos.OrderTypeViewDto;
import app.restaurant.models.entities.OrderType;
import app.restaurant.repositories.OrderTypeRepository;
import app.restaurant.services.OrderTypeService;
import app.restaurant.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        return orderTypeRepository.findAllTablesSortedByName().stream().map(t -> modelMapper.map(t, OrderTypeViewDto.class))
                .collect(Collectors.toList());
    }
}
