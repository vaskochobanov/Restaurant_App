document.addEventListener("DOMContentLoaded", () => {
  let filterField = document.getElementById("filterField");
  let waiterId = document.getElementById("waiterId");
  let tableId = document.getElementById("tableId");
  let mainContent = document.getElementById("mainContent");
  let createRow = (el) => {
    let divWrappingCard = document.createElement("div");
    mainContent.appendChild(divWrappingCard);
    divWrappingCard.classList.add("card");
    let divMealType = document.createElement("div");
    divWrappingCard.appendChild(divMealType);
    divMealType.classList.add("card-header", "waiter-menu");
    divMealType.innerText = el.type;
    let divCardBody = document.createElement("div");
    divWrappingCard.appendChild(divCardBody);
    divCardBody.classList.add("card-body", "waiter-menu");
    let h5MealName = document.createElement("h5");
    divCardBody.appendChild(h5MealName);
    h5MealName.classList.add("card-title");
    h5MealName.innerText = el.name;
    let pMealDescription = document.createElement("p");
    divCardBody.appendChild(pMealDescription);
    pMealDescription.classList.add("card-text", "waiter-order-meals");
    pMealDescription.innerText = el.description;
    let pMealIngredients = document.createElement("p");
    divCardBody.appendChild(pMealIngredients);
    pMealIngredients.classList.add("card-text", "waiter-order-meals");
    pMealIngredients.innerText = el.ingredients;
    let divMealButtonsHolder = document.createElement("div");
    divCardBody.appendChild(divMealButtonsHolder);
    divMealButtonsHolder.classList.add("menu-buttons-holder-waiter");
    let minusButton = document.createElement("button");
    divMealButtonsHolder.appendChild(minusButton);
    minusButton.type = "button";
    minusButton.classList.add("btn", "btn-danger", "menu-buttons");
    minusButton.innerText = "-";
    minusButton.addEventListener("click", () => {
      let currentNumber = Number(mealsCount.value);
      currentNumber--;
      mealsCount.value = currentNumber;
    });
    let mealsCount = document.createElement("input");
    divMealButtonsHolder.appendChild(mealsCount);
    mealsCount.type = "text";
    mealsCount.classList.add("form-control", "menu-meal-count");
    mealsCount.value = "0";
    let mealHiddenId = document.createElement("input");
    divMealButtonsHolder.appendChild(mealHiddenId);
    mealHiddenId.type = "hidden";
    mealHiddenId.classList.add("mealHiddenId");
    mealHiddenId.value = `${el.id}`;
    let plusButton = document.createElement("button");
    divMealButtonsHolder.appendChild(plusButton);
    plusButton.type = "button";
    plusButton.classList.add("btn", "btn-success", "menu-buttons");
    plusButton.innerText = "+";
    plusButton.addEventListener("click", () => {
      let currentNumber = Number(mealsCount.value);
      currentNumber++;
      mealsCount.value = currentNumber;
    });
  };
  fetch("http://localhost:8080/api/waiter-menu", {
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((data) => {
      let strigifiedResponce = JSON.stringify(data);
      let mealsArr = JSON.parse(strigifiedResponce);
      let filteredArr = [...mealsArr];
      filterField.addEventListener("input", (event) => {
        mainContent.innerHTML = "";
        filteredArr = mealsArr.filter((m) =>
          m.name.toLowerCase().includes(event.target.value.toLowerCase())
        );
        filteredArr.forEach((m) => createRow(m));
      });
      filteredArr.forEach((m) => {
        createRow(m);
      });
      let placeOrderButton = document.createElement("button");
      placeOrderButton.type = "submit";
      placeOrderButton.id = "placeOrder";
      placeOrderButton.classList.add("btn", "btn-success", "btn-place-order");
      placeOrderButton.innerText = "Place Order";
      let formNewOrder = document.createElement("form");
      formNewOrder.appendChild(placeOrderButton);
      mainContent.appendChild(formNewOrder);
      formNewOrder.method = "GET";
      formNewOrder.action = "/home";
      formNewOrder.classList.add("form-place-order");
      placeOrderButton.addEventListener("click", () => {
        let inputFieldsArr = document.getElementsByClassName("menu-meal-count");
        let mealIdsArr = document.getElementsByClassName("mealHiddenId");
        let resultOrder = [];
        for (let i = 0; i < inputFieldsArr.length; i++) {
          if (Number(inputFieldsArr[i].value)) {
            let current = {
              mealId: Number(mealIdsArr[i].value),
              quantity: Number(inputFieldsArr[i].value),
              waiterId: Number(waiterId.value),
              tableId: Number(tableId.value),
            };
            resultOrder.push(current);
          }
        }
        fetch("http://localhost:8080/api/new-order", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(resultOrder),
        });
      });
      window.addEventListener("pageshow", (event) => {
        filterField.value = "";
      });
    });
});
