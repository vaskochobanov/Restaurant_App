document.addEventListener("DOMContentLoaded", () => {
  let filterField = document.getElementById("filterField");
  let tableBody = document.getElementById("tableBody");
  let createRow = (el) => {
    let newTR = document.createElement("tr");
    let tdName = document.createElement("td");
    tdName.innerText = el.name;
    newTR.appendChild(tdName);
    let tdPrice = document.createElement("td");
    tdPrice.innerText = el.price;
    newTR.appendChild(tdPrice);
    let tdActive = document.createElement("td");
    tdActive.innerText = el.active;
    if (el.active) {
      tdActive.classList.add("table-cell-color-green");
    } else {
      tdActive.classList.add("table-cell-color-red");
    }
    newTR.appendChild(tdActive);
    let tdPromoted = document.createElement("td");
    tdPromoted.innerText = el.promoted;
    if (el.promoted) {
      tdPromoted.classList.add("table-cell-color-green");
    } else {
      tdPromoted.classList.add("table-cell-color-red");
    }
    newTR.appendChild(tdPromoted);
    let tdButtons = document.createElement("td");
    let formButton = document.createElement("div");
    formButton.classList.add("form-button");
    tdButtons.appendChild(formButton);
    let formEdit = document.createElement("form");
    formButton.appendChild(formEdit);
    formEdit.method = "GET";
    formEdit.action = `/meals/single-edit/${el.id}`;
    let editButton = document.createElement("button");
    formEdit.appendChild(editButton);
    editButton.innerText = "Edit";
    editButton.type = "submit";
    editButton.classList.add("btn");
    editButton.classList.add("btn-warning");
    let formDelete = document.createElement("form");
    formButton.appendChild(formDelete);
    formDelete.method = "GET";
    formDelete.action = `/meals/delete/${el.id}`;
    let deleteButton = document.createElement("button");
    formDelete.appendChild(deleteButton);
    formDelete.classList.add("form-button-delete");
    deleteButton.innerText = "Delete";
    deleteButton.type = "submit";
    deleteButton.classList.add("btn");
    deleteButton.classList.add("btn-danger");
    newTR.appendChild(tdButtons);
    tableBody.appendChild(newTR);
  }
  fetch("http://localhost:8080/api/meals", {
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
          tableBody.innerHTML = "";
        filteredArr = mealsArr.filter((m) =>
          m.name.includes(event.target.value)
        );
        filteredArr.forEach(m => createRow(m));
    });
    filteredArr.forEach((m) => {
      createRow(m);
      });
    });
});
