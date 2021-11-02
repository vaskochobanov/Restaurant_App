document.addEventListener("DOMContentLoaded", () => {
  let ingredientDropDownHolder = document.getElementById(
    "ingredientDropDownHolder"
  );
  let rowDiv = document.createElement("div");
  ingredientDropDownHolder.appendChild(rowDiv);
  let createRow = () => {
    let dropDownDiv = document.createElement("div");
    rowDiv.appendChild(dropDownDiv);
    dropDownDiv.classList.add("col-sm-10", "ingredient-inline");
    let selectMenu = document.createElement("select");
    dropDownDiv.appendChild(selectMenu);
    selectMenu.classList.add("form-select", "ingredient-option");
    let firstOption = document.createElement("option");
    selectMenu.appendChild(firstOption);
    firstOption.value = "";
    firstOption.innerText = "Select ingredient";
    firstOption.selected = true;
    fetch("http://localhost:8080/api/ingredients-names", {
      headers: {
        "Content-Type": "application/json",
      }
    }).then(res => res.json()).then(data => {
      let strigifiedResponce = JSON.stringify(data);
      let ingredientsArr = JSON.parse(strigifiedResponce);
      ingredientsArr.forEach(i => {
        let currentOption = document.createElement("option");
        selectMenu.appendChild(currentOption);
        currentOption.value = i;
        currentOption.innerText = i;
      });
      let inputCount = document.createElement("input");
      dropDownDiv.appendChild(inputCount);
      inputCount.classList.add("form-control", "ingredient-count");
      inputCount.type = "number";
      inputCount.step = "1";
      selectMenu.addEventListener("change", () => {
        inputCount.value = "1";
      });
    });
  };
  createRow();
  let addIngredientRowButton = document.createElement("button");
  ingredientDropDownHolder.appendChild(addIngredientRowButton);
  addIngredientRowButton.classList.add("btn", "btn-success", "meal-add-ingredient-button");
  addIngredientRowButton.innerText = "+ Ingredient";
  addIngredientRowButton.type = "button";
  addIngredientRowButton.addEventListener("click", createRow);
  let imageUrl = document.getElementById("imageUrl");
  let ingredientsField = document.getElementById("ingredients");
  ingredientsField.value = "";
  imageUrl.addEventListener("focus", () => {
    if (!ingredientsField.value) {
      let allIngredientsFields = document.getElementsByClassName("ingredient-option");
      let allCounts = document.getElementsByClassName("ingredient-count");
      let ingredientsString = "";
      for (let i = 0; i < allIngredientsFields.length; i++) {
        ingredientsString += `${allIngredientsFields[i].value}-${allCounts[i].value},`;
      }
      ingredientsField.value = ingredientsString;
    }
  });
});
