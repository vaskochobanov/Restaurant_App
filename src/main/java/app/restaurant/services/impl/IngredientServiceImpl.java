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
            mayo.setImageUrl("data:image/jpeg;base64,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");
            mayo.setName("mayonnaise");
            mayo.setQuantity(20.0);
            ingredientRepository.save(mayo);
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
