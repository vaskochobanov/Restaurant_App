package app.restaurant.services.impl;

import app.restaurant.models.bindings.IngredientAddBindingModel;
import app.restaurant.models.dtos.IngredientViewDto;
import app.restaurant.models.entities.Ingredient;
import app.restaurant.repositories.IngredientRepository;
import app.restaurant.services.IngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, ModelMapper modelMapper) {
        this.ingredientRepository = ingredientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addIngredient(IngredientAddBindingModel ingredientAddBindingModel) {
        Ingredient toAdd = modelMapper.map(ingredientAddBindingModel, Ingredient.class);
        toAdd.setName(ingredientAddBindingModel.getName().toLowerCase());
        ingredientRepository.save(toAdd);
    }

    @Override
    public void initIngredients() {
        if (ingredientRepository.count() == 0) {
            Ingredient cucumbers = new Ingredient();
            cucumbers.setBestBefore(LocalDate.of(2021, 11, 11));
            cucumbers.setName("cucumbers");
            cucumbers.setQuantity(10.0);
            cucumbers.setImageUrl("https://cdn.mos.cms.futurecdn.net/EBEXFvqez44hySrWqNs3CZ.jpg");
            ingredientRepository.save(cucumbers);
            Ingredient tomatoes = new Ingredient();
            tomatoes.setBestBefore(LocalDate.of(2021, 11, 11));
            tomatoes.setName("tomatoes");
            tomatoes.setQuantity(10.0);
            tomatoes.setImageUrl("https://www.vegetamo.it/wp-content/uploads/2018/04/pomodori.jpg");
            ingredientRepository.save(tomatoes);
            Ingredient oliveOil = new Ingredient();
            oliveOil.setBestBefore(LocalDate.of(2022, 10, 17));
            oliveOil.setImageUrl("https://produits.bienmanger.com/18473-0w470h470_Organic_Olive_Oil_Kanakis.jpg");
            oliveOil.setName("olive oil");
            oliveOil.setQuantity(5.0);
            ingredientRepository.save(oliveOil);
            Ingredient fetaCheese = new Ingredient();
            fetaCheese.setBestBefore(LocalDate.of(2021, 12, 17));
            fetaCheese.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Feta_Cheese.jpg/1200px-Feta_Cheese.jpg");
            fetaCheese.setName("feta cheese");
            fetaCheese.setQuantity(10.0);
            ingredientRepository.save(fetaCheese);
            Ingredient salt = new Ingredient();
            salt.setBestBefore(LocalDate.of(2024, 10, 17));
            salt.setImageUrl("https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322745_1100-732x549.jpg");
            salt.setName("salt");
            salt.setQuantity(5.0);
            ingredientRepository.save(salt);
            Ingredient flour = new Ingredient();
            flour.setBestBefore(LocalDate.of(2022, 10, 20));
            flour.setImageUrl("https://www.e-fresco.io/pub/media/catalog/product/u/2/u21d2f12fc6a44ebca4ed87518c148325h.jpg");
            flour.setName("flour");
            flour.setQuantity(10.0);
            ingredientRepository.save(flour);
            Ingredient yeast = new Ingredient();
            yeast.setBestBefore(LocalDate.of(2024, 10, 20));
            yeast.setImageUrl("https://www.thespruceeats.com/thmb/3NSkXqEuwMbGDj8Qcx-WscC0Ogo=/1837x1378/smart/filters:no_upscale()/Freshanddriedyeast-GettyImages-126372759-59286bbd3df78cbe7ea02b40.jpg");
            yeast.setName("yeast");
            yeast.setQuantity(0.5);
            ingredientRepository.save(yeast);
            Ingredient ham = new Ingredient();
            ham.setBestBefore(LocalDate.of(2021, 12, 20));
            ham.setImageUrl("https://imagesvc.meredithcorp.io/v3/mm/image?q=85&c=sc&poi=face&w=2000&h=1000&url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F19%2F2018%2F02%2F13%2Ffield-image-ham-slices-hero-2000.jpg");
            ham.setName("ham");
            ham.setQuantity(10.0);
            ingredientRepository.save(ham);
            Ingredient mozzarella = new Ingredient();
            mozzarella.setBestBefore(LocalDate.of(2022, 3, 20));
            mozzarella.setImageUrl("https://www.alphafoodie.com/wp-content/uploads/2020/08/Mozzarella-cheese.jpeg");
            mozzarella.setName("mozzarella");
            mozzarella.setQuantity(10.0);
            ingredientRepository.save(mozzarella);
            Ingredient beer = new Ingredient();
            beer.setBestBefore(LocalDate.of(2022, 10, 23));
            beer.setImageUrl("https://thumbs.dreamstime.com/b/glass-draft-beer-isolated-white-background-171472670.jpg");
            beer.setName("beer");
            beer.setQuantity(200.0);
            ingredientRepository.save(beer);
            Ingredient oranges = new Ingredient();
            oranges.setBestBefore(LocalDate.of(2022, 10, 23));
            oranges.setImageUrl("https://produits.bienmanger.com/33285-0w470h470_Organic_Fresh_Oranges_From_Italy_New_Hall.jpg");
            oranges.setName("Oranges");
            oranges.setQuantity(100.0);
            Ingredient cola = new Ingredient();
            cola.setBestBefore(LocalDate.of(2023, 10, 23));
            cola.setImageUrl("https://cdncloudcart.com/16398/products/images/39404/gazirana-napitka-coca-cola-ken-330-ml-image_5ea2cc6235fb6_800x800.png?1587731937");
            cola.setName("coca cola");
            cola.setQuantity(100.0);
            ingredientRepository.save(cola);
            Ingredient potatoes = new Ingredient();
            potatoes.setBestBefore(LocalDate.of(2022, 12, 26));
            potatoes.setImageUrl("https://media.istockphoto.com/photos/three-potatoes-picture-id157430678?k=20&m=157430678&s=612x612&w=0&h=dfYLuPYwA50ojI90hZ4jCgKZd5TGnqf24UCVBszoZIA=");
            potatoes.setName("potatoes");
            potatoes.setQuantity(100.0);
            ingredientRepository.save(potatoes);
            Ingredient carrots = new Ingredient();
            carrots.setBestBefore(LocalDate.of(2021, 12, 26));
            carrots.setImageUrl("https://www.eatthis.com/wp-content/uploads/sites/4/2021/07/bunch-carrots.jpg?fit=1200%2C879&ssl=1");
            carrots.setName("carrots");
            carrots.setQuantity(50.0);
            ingredientRepository.save(carrots);
            Ingredient corn = new Ingredient();
            corn.setBestBefore(LocalDate.of(2023, 10, 26));
            corn.setImageUrl("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/tinned-sweet-corn-royalty-free-image-1602860110.jpg?crop=1.00xw:1.00xh;0,0&resize=640:*");
            corn.setName("corn");
            corn.setQuantity(10.0);
            ingredientRepository.save(corn);
            Ingredient mayo = new Ingredient();
            mayo.setBestBefore(LocalDate.of(2021, 12, 26));
            mayo.setImageUrl("https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/79284804-001A-4D3C-AE28-7578D6DE26A8/Derivates/CA01B5C2-D45F-4FB1-8EAA-EEE7F93EA1F2.jpg");
            mayo.setName("mayonnaise");
            mayo.setQuantity(20.0);
            ingredientRepository.save(mayo);
            Ingredient rice = new Ingredient();
            rice.setBestBefore(LocalDate.of(2025, 10,26));
            rice.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzLWYdcyEb_sGp_KDs9i3UerLqTGyjI9dslw&usqp=CAU");
            rice.setName("rice");
            rice.setQuantity(20.0);
            ingredientRepository.save(rice);
            Ingredient frozenVegetables = new Ingredient();
            frozenVegetables.setBestBefore(LocalDate.of(2025, 10,26));
            frozenVegetables.setImageUrl("https://media.healthyfood.com/wp-content/uploads/2020/05/10-ways-with-frozen-vegetables-iStock-182197343-500x409.jpg");
            frozenVegetables.setName("frozen vegetables");
            frozenVegetables.setQuantity(20.0);
            ingredientRepository.save(frozenVegetables);
            Ingredient butter = new Ingredient();
            butter.setBestBefore(LocalDate.of(2021, 12, 26));
            butter.setImageUrl("https://www.kirbysproduce.com/wp-content/uploads/2020/04/butter.jpg");
            butter.setName("butter");
            butter.setQuantity(10.0);
            ingredientRepository.save(butter);
            Ingredient soySauce = new Ingredient();
            soySauce.setBestBefore(LocalDate.of(2025, 10, 26));
            soySauce.setImageUrl("https://www.asianfoodlovers.com/media/catalog/product/cache/8/image/750x750/9df78eab33525d08d6e5fb8d27136e95/8/2/8295_7.jpg");
            soySauce.setName("soy sauce");
            soySauce.setQuantity(5.0);
            ingredientRepository.save(soySauce);
        }
    }

    @Override
    public List<IngredientViewDto> getAllIngredientsSorted() {
        return ingredientRepository.findAllSortedByExpiryDate().stream().map(i -> modelMapper.map(i, IngredientViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name).orElse(null);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }
}
