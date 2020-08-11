package pizzas.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pizzas.*;
import pizzas.Ingredient.Type;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("THIN", "Thin Crust", Ingredient.Type.CRUST),
                new Ingredient("FLAT", "Flatbread Crust",
                        Ingredient.Type.CRUST),
                new Ingredient("HAM", "Ham",
                        Ingredient.Type.PROTEIN),
                new Ingredient("HOTSAUSAGE", "Hot Sausage",
                        Ingredient.Type.PROTEIN),
                new Ingredient("SPINACH", "Spinach",
                        Ingredient.Type.VEGGIES),
                new Ingredient("MUSH", "Mushroom",
                        Ingredient.Type.VEGGIES),
                new Ingredient("MOZ", "Mozzarella Cheese",
                        Ingredient.Type.CHEESE),
                new Ingredient("CHE", "Cheddar Cheese",
                        Ingredient.Type.CHEESE),
                new Ingredient("CLAS", "Classical Pizza Sauce",
                        Ingredient.Type.SAUCE),
                new Ingredient("ORG", "Organic Pizza Sauce",
                        Ingredient.Type.SAUCE)
        );
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Pizza());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Pizza design, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        // Save the design
        // TODO: Implement saving the design
        log.info("Processing design: " + design);
        // redirect user to current orders
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
            return ingredients
                    .stream()
                    .filter(x -> x.getType().equals(type))
                    .collect(Collectors.toList());
    }

}
