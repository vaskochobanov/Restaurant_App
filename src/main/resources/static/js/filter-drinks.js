import * as generate from "/js/export-methods.js";
document.addEventListener("DOMContentLoaded", () => {
  let filterField = document.getElementById("filterField");
  let contentBody = document.getElementById("mainContent");
  fetch("http://localhost:8080/api/drinks", {
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
        contentBody.innerHTML = "";
        filteredArr = mealsArr.filter((m) =>
          m.mealName.toLowerCase().includes(event.target.value.toLowerCase())
        );
        filteredArr.forEach((m) => generate.createRow(m));
      });
      filteredArr.forEach((m) => {
        generate.createRow(m);
      });
    window.addEventListener("pageshow", (event) => {
      filterField.value = "";
    });
  });
});
