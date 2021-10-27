package app.restaurant.services.impl;

import app.restaurant.models.bindings.MealAddBindingModel;
import app.restaurant.models.bindings.MealEditBindingModel;
import app.restaurant.models.dtos.MealViewDto;
import app.restaurant.models.entities.Ingredient;
import app.restaurant.models.entities.Meal;
import app.restaurant.models.entities.enums.MealType;
import app.restaurant.repositories.MealRepository;
import app.restaurant.services.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final ModelMapper modelMapper;

    public MealServiceImpl(MealRepository mealRepository, ModelMapper modelMapper) {
        this.mealRepository = mealRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addMeal(MealAddBindingModel mealAddBindingModel) {
        Meal tryToFind = mealRepository.findByName(mealAddBindingModel.getName()).orElse(null);
        if (tryToFind != null) {
            return true;
        }
        Meal toAdd = modelMapper.map(mealAddBindingModel, Meal.class);
        toAdd.setType(MealType.valueOf(mealAddBindingModel.getType()));
        mealRepository.save(toAdd);
        return false;
    }

    @Override
    public void initMeals() {
        if (mealRepository.count() == 0) {
        Meal beer = new Meal();
        beer.setActive(true);
        beer.setDescription("A 500ml glass of draft beer.");
        beer.setImageUrl("https://thumbs.dreamstime.com/b/glass-draft-beer-isolated-white-background-171472670.jpg");
        beer.setIngredients("beer-0.5");
        beer.setName("Beer");
        beer.setPrice(2.49);
        beer.setType(MealType.DRINK);
        mealRepository.save(beer);
        Meal shopskaSalad = new Meal();
        shopskaSalad.setActive(true);
        shopskaSalad.setDescription("A mixture of cucumbers, tomatoes and feta cheese");
        shopskaSalad.setImageUrl("https://www.wandercooks.com/wp-content/uploads/2019/07/bulgarian-shopska-salad-ft2.jpg");
        shopskaSalad.setIngredients("cucumbers-0.3,tomatoes-0.2,feta cheese-0.15,olive oil-0.03,salt-0.01");
        shopskaSalad.setName("Shopska Salad");
        shopskaSalad.setPrice(8.99);
        shopskaSalad.setType(MealType.SALAD);
        mealRepository.save(shopskaSalad);
        Meal pizza = new Meal();
        pizza.setActive(true);
        pizza.setDescription("A classic italian pizza.");
        pizza.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg");
        pizza.setIngredients("flour-0.1,yeast-0.005,tomato sauce-0.1,ham-0.2,mozzarella-0.1");
        pizza.setName("Pizza");
        pizza.setPrice(10.99);
        pizza.setPromoted(true);
        pizza.setType(MealType.MAIN_DISH);
        mealRepository.save(pizza);
        Meal orangeJuice = new Meal();
        orangeJuice.setActive(true);
        orangeJuice.setDescription("A glass of freshly squeezed orange juice.");
        orangeJuice.setImageUrl("https://greenletes.com/wp-content/uploads/2018/05/orange-juice-1024x1024.png");
        orangeJuice.setIngredients("oranges-0.5");
        orangeJuice.setName("Orange Juice");
        orangeJuice.setPrice(4.99);
        orangeJuice.setPromoted(false);
        orangeJuice.setType(MealType.DRINK);
        mealRepository.save(orangeJuice);
        Meal cola = new Meal();
        cola.setActive(true);
        cola.setDescription("A can of classic Coca Cola");
        cola.setImageUrl("https://cdncloudcart.com/16398/products/images/39404/gazirana-napitka-coca-cola-ken-330-ml-image_5ea2cc6235fb6_800x800.png?1587731937");
        cola.setIngredients("cola-0.3");
        cola.setName("Coca Cola");
        cola.setPrice(1.50);
        cola.setPromoted(false);
        cola.setType(MealType.DRINK);
        mealRepository.save(cola);
        Meal frenchSalad = new Meal();
        frenchSalad.setActive(true);
        frenchSalad.setDescription("French salad");
        frenchSalad.setImageUrl("https://recepti.gotvach.bg/files/lib/400x296/ruska-salata-forma.jpg");
        frenchSalad.setIngredients("potatoes-0.1,carrots-0.05,corn-0.05,ham-0.05,mayonnaise-0.1");
        frenchSalad.setName("French Salad");
        frenchSalad.setPrice(7.50);
        frenchSalad.setPromoted(false);
        frenchSalad.setType(MealType.SALAD);
        mealRepository.save(frenchSalad);
        Meal risotto = new Meal();
        risotto.setActive(true);
        risotto.setDescription("Imperial rice with frozen vegetables with butter and soy sauce");
        risotto.setImageUrl("https://www.acouplecooks.com/wp-content/uploads/2021/03/Mushroom-Risotto-055.jpg");
        risotto.setIngredients("rice-0.1,frozen vegetables-0.1,butter-0.03,soy sauce-0.01");
        risotto.setName("Risotto");
        risotto.setPrice(7.99);
        risotto.setPromoted(false);
        risotto.setType(MealType.MAIN_DISH);
        mealRepository.save(risotto);
        Meal banica = new Meal();
        banica.setActive(true);
        banica.setDescription("Tradiotional balcanic dish");
        banica.setImageUrl("https://i.ytimg.com/vi/VT4I-VSODRE/maxresdefault.jpg");
        banica.setIngredients("kori-0.3,feta cheese-0.05,yogurt-0.1,eggs-2");
        banica.setName("Banica");
        banica.setPrice(4.99);
        banica.setPromoted(false);
        banica.setType(MealType.MAIN_DISH);
        mealRepository.save(banica);
        }
    }

    @Override
    public List<MealViewDto> getAllMealsSorted() {
        return mealRepository.findAllMealsSorted().stream().map(m -> modelMapper.map(m, MealViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    @Override
    public MealViewDto getMealById(Long id) {
        return modelMapper.map(mealRepository.findById(id).orElse(null), MealViewDto.class);
    }

    @Override
    public void editMeal(MealEditBindingModel mealEditBindingModel) {
        Meal toEdit = mealRepository.findById(mealEditBindingModel.getId()).orElse(null);
        toEdit.setPrice(mealEditBindingModel.getPrice());
        toEdit.setIngredients(mealEditBindingModel.getIngredients());
        toEdit.setActive(mealEditBindingModel.isActive());
        toEdit.setPromoted(mealEditBindingModel.isPromoted());
        mealRepository.save(toEdit);
    }

    @Override
    public Meal getMealByName(String name) {
        return mealRepository.findByName(name).orElse(null);
    }
}
