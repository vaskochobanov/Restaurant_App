document.addEventListener("DOMContentLoaded", () => {
  let filterField = document.getElementById("filterField");
  let customerId = document.getElementById("customerId");
  let tableId = document.getElementById("tableId");
  let mainContent = document.getElementById("mainContent");
  let divCardsContainer = document.createElement("div");
  mainContent.appendChild(divCardsContainer);
  divCardsContainer.classList.add("card-wrapper", "row", "row-cols-1", "row-cols-md-3", "g-4");
  let totalSumEl = document.getElementById("totalSum");
  let totalSum = 0;
  totalSumEl.innerText = `Total Sum: ${totalSum} $`;
  let createCard = (el) => {
    let divWrappingCard = document.createElement("div");
    divCardsContainer.appendChild(divWrappingCard);
    divWrappingCard.classList.add("card", "cards");
    let mealImg = document.createElement("img");
    divWrappingCard.appendChild(mealImg);
    mealImg.classList.add("card-img-top");
    mealImg.src = el.imageUrl;
    let divCardBody = document.createElement("div");
    divWrappingCard.appendChild(divCardBody);
    divCardBody.classList.add("card-body", "cust-card-body");
    let h5MealName = document.createElement("h5");  
    divCardBody.appendChild(h5MealName);
    h5MealName.classList.add("card-title");
    h5MealName.innerText = el.name;
    let pMealDescription = document.createElement("p");
    divCardBody.appendChild(pMealDescription);
    pMealDescription.classList.add("card-text");
    pMealDescription.innerText = el.description;
    let h5Price = document.createElement("h5");
    divCardBody.appendChild(h5Price);
    h5Price.classList.add("card-title");
    h5Price.innerText = `${el.price} $`;
    let pMealIngredients = document.createElement("p");
    divCardBody.appendChild(pMealIngredients);
    pMealIngredients.classList.add("card-text");
    pMealIngredients.innerText = el.ingredients;
    let divMealButtonsHolder = document.createElement("div");
    divCardBody.appendChild(divMealButtonsHolder);
    divMealButtonsHolder.classList.add("menu-buttons-holder");
    let minusButton = document.createElement("button");
    divMealButtonsHolder.appendChild(minusButton);
    minusButton.type = "button";
    minusButton.classList.add("btn", "btn-danger", "menu-buttons");
    minusButton.innerText = "-";
    minusButton.addEventListener("click", () => {
      let currentNumber = Number(mealsCount.value);
      if (currentNumber > 0) {
          currentNumber--;
          if (currentNumber < 0) {
              currentNumber = 0;
          }
          mealsCount.value = currentNumber;
          totalSum -= el.price;
          if (totalSum < 0) {
              totalSum = 0;
          }
          totalSumEl.innerText = `Total Sum: ${totalSum.toFixed(2)} $`;
      }
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
      totalSum += el.price;
      totalSumEl.innerText = `Total Sum: ${totalSum.toFixed(2)} $`;
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
        divCardsContainer.innerHTML = "";
        filteredArr = mealsArr.filter((m) =>
          m.name.toLowerCase().includes(event.target.value.toLowerCase())
        );
        filteredArr.forEach((m) => createCard(m));
      });
      filteredArr.forEach((m) => {
        createCard(m);
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
      formNewOrder.action = "/home-customer-order";
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
                      customerId: Number(customerId.value),
                      tableId: Number(tableId.value),
                    };
                    resultOrder.push(current);
                }
            }
            fetch("http://localhost:8080/api/new-order-cust", {
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
