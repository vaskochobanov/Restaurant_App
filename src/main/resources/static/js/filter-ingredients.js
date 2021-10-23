document.addEventListener("DOMContentLoaded", () => {
    let filterField = document.getElementById("filterField");
    let tableBody = document.getElementById("tableBody");
    let createRow = (el) => {
      let newTR = document.createElement("tr");
      let tdImage = document.createElement("td");
      newTR.appendChild(tdImage);
      let divImage = document.createElement("div");
      tdImage.appendChild(divImage);
      divImage.classList.add("ingredient-table-row");
      let imgImage = document.createElement("img");
      divImage.appendChild(imgImage);
      imgImage.classList.add("ingredient-image");
      imgImage.src = el.imageUrl;
      let tdName = document.createElement("td");
      newTR.appendChild(tdName);
      let divName = document.createElement("div");
      tdName.appendChild(divName);
      divName.classList.add("ingredient-table-row");
      divName.innerText = el.name;
      let tdQuantity = document.createElement("td");
      newTR.appendChild(tdQuantity);
      let divQuantity = document.createElement("div");
      tdQuantity.appendChild(divQuantity);
      divQuantity.classList.add("ingredient-table-row");
      divQuantity.innerText = el.quantity;
      let tdBestBefore = document.createElement("td");
      newTR.appendChild(tdBestBefore);
      let divBestBefore = document.createElement("div");
      tdBestBefore.appendChild(divBestBefore);
      divBestBefore.classList.add("ingredient-table-row");
      divBestBefore.innerText = el.bestBefore;
      let tdButton = document.createElement("td");
      newTR.appendChild(tdButton);
      let divButton = document.createElement("div");
      tdButton.appendChild(divButton);
      divButton.classList.add("form-button");
      let formButton = document.createElement("form");
      divButton.appendChild(formButton);
      formButton.method = "GET";
      formButton.action = `/ingredients/delete/${el.id}`;
      formButton.classList.add("form-button-delete");
      let deleteButton = document.createElement("button");
      formButton.appendChild(deleteButton);
      deleteButton.type = "submit";
      deleteButton.classList.add("btn");
      deleteButton.classList.add("btn-danger");
      deleteButton.innerText = "Delete";
      tableBody.appendChild(newTR);
    }
    fetch("http://localhost:8080/api/ingredients", {
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
            m.name.toLowerCase().includes(event.target.value.toLowerCase())
          );
          filteredArr.forEach(m => createRow(m));
      });
      filteredArr.forEach((m) => {
        createRow(m);
        });
      });
      window.addEventListener("pageshow", (event) => {
        filterField.value = "";
      });
  });