let contentBody = document.getElementById("mainContent");
export let createRow = (el) => {
  let mealCard = document.createElement("div");
  contentBody.appendChild(mealCard);
  mealCard.classList.add("card", "meal-preparation");
  let mealCardBody = document.createElement("div");
  mealCard.appendChild(mealCardBody);
  mealCardBody.classList.add("card-body");
  let mealCount = document.createElement("h5");
  mealCardBody.appendChild(mealCount);
  mealCount.classList.add("card-title");
  mealCount.innerText = `${el.mealName} x${el.count}`;
  let ingredientsArr = el.mealIngredients.split(",");
  ingredientsArr.forEach((i) => {
    let mealIngredients = document.createElement("p");
    mealCardBody.appendChild(mealIngredients);
    mealIngredients.classList.add("card-text");
    mealIngredients.innerText = i;
  });
  let form = document.createElement("form");
  mealCardBody.appendChild(form);
  form.method = "POST";
  form.action = `/prep/${el.id}`;
  let submitButton = document.createElement("button");
  form.appendChild(submitButton);
  submitButton.type = "submit";
  submitButton.classList.add("btn", "btn-success");
  submitButton.innerText = "Prepare";
};
