document.addEventListener("DOMContentLoaded", () => {
    let customerId = document.getElementById("customerId");
    let mainContent = document.getElementById("mainContent");
    fetch(`http://localhost:8080/api/cust-order/${customerId.value}`).then(res => res.json()).then(data => {
        let strigifiedResponce = JSON.stringify(data);
        let orderData = JSON.parse(strigifiedResponce);
        let divWrappingCard = document.createElement("div");
        mainContent.appendChild(divWrappingCard);
        divWrappingCard.classList.add("card", "customer-order");
        divWrappingCard.style = "width: 18rem;";
        let divUpperCardBody = document.createElement("div");
        divWrappingCard.appendChild(divUpperCardBody);
        divUpperCardBody.classList.add("card-body", "cust-order-card-body");
        let h5Title = document.createElement("h5");
        divUpperCardBody.appendChild(h5Title);
        h5Title.classList.add("card-title");
        h5Title.innerText = "Online Order";
        let pPrice = document.createElement("p");
        divUpperCardBody.appendChild(pPrice);
        pPrice.classList.add("card-text");
        pPrice.innerText = `${orderData[0].totalSum.toFixed(2)} $`;
        let ulMeals = document.createElement("ul");
        divWrappingCard.appendChild(ulMeals);
        ulMeals.classList.add("list-group", "list-group-flush");
        orderData.forEach(m => {
            let currentLi = document.createElement("li");
            ulMeals.appendChild(currentLi);
            currentLi.classList.add("list-group-item", "cust-order-meal-item");
            if (m.prepared) {
                currentLi.classList.add("waiter-meal-done");
            }
            else {
                currentLi.classList.add("waiter-meal-preparing");
            }
            currentLi.innerText = m.mealName;
        });
    });
})