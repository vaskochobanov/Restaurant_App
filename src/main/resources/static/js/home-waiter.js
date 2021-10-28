document.addEventListener("DOMContentLoaded", () => {
    let filterField = document.getElementById("filterField");
    let waiterId = document.getElementById("waiterId");
    let mainContent = document.getElementById("mainContent");
    let createRow = (el) => {
        let outterDiv = document.createElement("div");
        mainContent.appendChild(outterDiv);
        outterDiv.classList.add("card", "table-waiter");
        let h5TableName = document.createElement("h5");
        outterDiv.appendChild(h5TableName);
        h5TableName.classList.add("card-header");
        let divCardBody = document.createElement("div");
        divCardBody.classList.add("card-body");
        h5TableName.innerText = el.name;
        if (el.free) {
            h5TableName.classList.add("table-name-free");
            outterDiv.appendChild(divCardBody);
            let newOrderForm = document.createElement("form");
            newOrderForm.method = "GET";
            newOrderForm.action = `/meals/meals-menu/${el.id}`;
            divCardBody.appendChild(newOrderForm);
            let newOrderButton = document.createElement("button");
            newOrderForm.appendChild(newOrderButton);
            newOrderButton.type = "submit";
            newOrderButton.classList.add("btn", "btn-success");
            newOrderButton.innerText = "New Order";
        }
        else {
            h5TableName.classList.add("table-name-occupied");
            let divGridHolder = document.createElement("div");
            outterDiv.appendChild(divGridHolder);
            divGridHolder.classList.add("waiter-grid-container");
            divGridHolder.appendChild(divCardBody);
            divCardBody.classList.add("waiter-table-divs");
            let h5Sum = document.createElement("h5");
            divCardBody.appendChild(h5Sum);
            h5Sum.classList.add("card-title", "table-check");
            h5Sum.innerText = el.sum;
            let endForm = document.createElement("form");
            divCardBody.appendChild(endForm);
            endForm.classList.add("waiter-end-button");
            endForm.method = "POST";
            let endButton = document.createElement("button");
            endForm.appendChild(endButton);
            endButton.type = "submit";
            endButton.classList.add("btn", "btn-danger");
            endButton.innerText = "End Order";
            let editOrderForm = document.createElement("form");
            divCardBody.appendChild(editOrderForm);
            editOrderForm.classList.add("waiter-edit-button");
            editOrderForm.method = "POST";
            let editButton = document.createElement("button");
            editOrderForm.appendChild(editButton);
            editButton.type = "submit";
            editButton.classList.add("btn", "btn-warning");
            editButton.innerText = "Edit Order";
            let divListMeals = document.createElement("div");
            divGridHolder.appendChild(divListMeals);
            divListMeals.classList.add("waiter-table-divs");
            el.listMeals.forEach(m => {
                let mealP = document.createElement("p");
                divListMeals.appendChild(mealP);
                mealP.innerText = m.mealName;
                mealP.classList.add("card-text", "waiter-order-meals");
                if (m.prepared) {
                    mealP.classList.add("waiter-meal-done");
                }
                else {
                    mealP.classList.add("waiter-meal-preparing");
                }
            });
        }

    };
    fetch(`http://localhost:8080/api/waiter-home/${waiterId.value}`, {
        headers: {
            "Content-Type": "application/json",
        },
    }).then(res => res.json()).then(data => {
        let strigifiedResponce = JSON.stringify(data);
        let waiterData = JSON.parse(strigifiedResponce);
        let filteredArr = [...waiterData];
        filterField.addEventListener("input", (event) => {
            mainContent.innerHTML = "";
            filteredArr = waiterData.filter((m) =>
              m.name.toLowerCase().includes(event.target.value.toLowerCase())
            );
            filteredArr.forEach((m) => createRow(m));
          });
          filteredArr.forEach((m) => {
            createRow(m);
          });
        window.addEventListener("pageshow", (event) => {
          filterField.value = "";
        });
    })
})