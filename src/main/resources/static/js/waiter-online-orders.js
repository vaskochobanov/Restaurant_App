document.addEventListener("DOMContentLoaded", () => {
  let mainContent = document.getElementById("mainContent");
  fetch("http://localhost:8080/api/waiter-online-orders", {
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((data) => {
      let strigifiedResponce = JSON.stringify(data);
      let ordersData = JSON.parse(strigifiedResponce);
      ordersData.forEach((od) => {
        let outterDiv = document.createElement("div");
        mainContent.appendChild(outterDiv);
        outterDiv.classList.add("card", "table-waiter");
        let h5TableName = document.createElement("h5");
        outterDiv.appendChild(h5TableName);
        h5TableName.classList.add("card-header", "waiter-online-title");
        h5TableName.innerText = `Online Order for ${od.customerName}`;
        let divGridHolder = document.createElement("div");
        outterDiv.appendChild(divGridHolder);
        divGridHolder.classList.add("waiter-grid-container");
        let divCardBody = document.createElement("div");
        divGridHolder.appendChild(divCardBody);
        divCardBody.classList.add("card-body", "waiter-table-divs");
        let h5Sum = document.createElement("h5");
        divCardBody.appendChild(h5Sum);
        h5Sum.classList.add("card-title", "table-check");
        h5Sum.innerText = od.totalSum.toFixed(2);
        let endForm = document.createElement("form");
        divCardBody.appendChild(endForm);
        endForm.classList.add("waiter-end-button");
        endForm.method = "GET";
        endForm.action = "/tables/online-orders";
        let endButton = document.createElement("button");
        endForm.appendChild(endButton);
        endButton.type = "submit";
        endButton.classList.add("btn", "btn-danger");
        endButton.innerText = "End Order";
        endButton.addEventListener("click", () => {
          fetch("http://localhost:8080/api/close-online-order", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(od.id),
          });
        });
        let divListMeals = document.createElement("div");
        divGridHolder.appendChild(divListMeals);
        divListMeals.classList.add("waiter-table-divs");
        od.listMeals.forEach((m) => {
          let mealP = document.createElement("p");
          divListMeals.appendChild(mealP);
          mealP.innerText = m.mealName;
          mealP.classList.add("card-text", "waiter-order-meals");
          if (m.prepared) {
            mealP.classList.add("waiter-meal-done");
          } else {
            mealP.classList.add("waiter-meal-preparing");
          }
        });
      });
    });
});
