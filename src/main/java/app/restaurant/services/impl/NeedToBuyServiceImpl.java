package app.restaurant.services.impl;

import app.restaurant.models.entities.NeedToBuy;
import app.restaurant.repositories.NeedToBuyRepository;
import app.restaurant.services.NeedToBuyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NeedToBuyServiceImpl implements NeedToBuyService {
    private final NeedToBuyRepository needToBuyRepository;

    public NeedToBuyServiceImpl(NeedToBuyRepository needToBuyRepository) {
        this.needToBuyRepository = needToBuyRepository;
    }

    @Override
    public void addToBuy(String ingredientName) {
        NeedToBuy needToBuy = new NeedToBuy();
        needToBuy.setIngredientName(ingredientName);
        needToBuyRepository.save(needToBuy);
    }

    @Override
    public List<String> getAllUniqueIngredientsToBuy() {
        List<String> result = new ArrayList<>();
        result = needToBuyRepository.findAllUniqueIngredients();
        if (result.size() == 0) {
            result.add("none");
            return result;
        }
        return result;
    }

    @Override
    public void deleteNeededIngredient(String name) {
        needToBuyRepository.deleteNeedToBuyByIngredientName(name);
    }
}
