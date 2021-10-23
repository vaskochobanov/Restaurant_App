document.addEventListener("DOMContentLoaded", () => {
  let filterField = document.getElementById("filterField");
  let tableBody = document.getElementById("tableBody");
  let createRow = (el) => {
    let newTR = document.createElement("tr");
    let tdFullName = document.createElement("td");
    newTR.appendChild(tdFullName);
    tdFullName.innerText = el.fullName;
    let tdUsername = document.createElement("td");
    newTR.appendChild(tdUsername);
    tdUsername.innerText = el.username;
    let tdButtons = document.createElement("td");
    let formButton = document.createElement("div");
    formButton.classList.add("form-button");
    tdButtons.appendChild(formButton);
    let formEdit = document.createElement("form");
    formButton.appendChild(formEdit);
    formEdit.method = "GET";
    formEdit.action = `/admin/user-edit/${el.id}`;
    let editButton = document.createElement("button");
    formEdit.appendChild(editButton);
    editButton.innerText = "Edit";
    editButton.type = "submit";
    editButton.classList.add("btn");
    editButton.classList.add("btn-warning");
    let formDelete = document.createElement("form");
    formButton.appendChild(formDelete);
    formDelete.method = "GET";
    formDelete.action = `/admin/user-delete/${el.id}`;
    let deleteButton = document.createElement("button");
    formDelete.appendChild(deleteButton);
    formDelete.classList.add("form-button-delete");
    deleteButton.innerText = "Delete";
    deleteButton.type = "submit";
    deleteButton.classList.add("btn");
    deleteButton.classList.add("btn-danger");
    newTR.appendChild(tdButtons);
    tableBody.appendChild(newTR);
  };
  fetch("http://localhost:8080/api/users", {
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
          m.fullName.toLowerCase().includes(event.target.value.toLowerCase())
        );
        filteredArr.forEach((m) => createRow(m));
      });
      filteredArr.forEach((m) => {
        createRow(m);
      });
    });
  window.addEventListener("pageshow", (event) => {
    filterField.value = "";
  });
});
