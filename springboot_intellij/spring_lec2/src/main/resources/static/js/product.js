window.onload = function() {

    loadProducts();
    insertProduct();
}
// 제이쿼리, 윈도우

function loadProducts() {

    fetch("/api/product/all")
        .then(response => response.json())
        .then(products => {
            console.log(products);

            const productList = document.getElementById("product-list");
            productList.innerHTML = "";

            products.forEach(product => {
                const item = document.querySelector(".item.temp").cloneNode(true);

                item.querySelector(".no").innerText = product.p_no;
                item.querySelector(".name").innerText = product.p_name;
                item.querySelector(".price").innerText = product.p_price;

                item.classList.remove("temp");
                productList.appendChild(item);

                item.lastElementChild.addEventListener("click", () => {
                    console.log(product.p_no);
                    deleteProduct(product.p_no);
                })
            })



        })


}

function deleteProduct(no) {
    fetch(`/api/product/${no}`,{
        method: "delete"
    })
        .then(response => response.json())
        .then((result) => {
            console.log(result);
            loadProducts();
        })
}

function insertProduct() {
    const addBtn = document.querySelector("#add");
    const nameEl = document.querySelector("#name");
    const priceEl = document.querySelector("input[name='p_price']");

    console.log(addBtn);
    console.log(nameEl);
    console.log(priceEl);


    addBtn.addEventListener("click", () => {
       console.log("add btn clicked..!");

       const obj = {
           p_name: nameEl.value,
           p_price: priceEl.value
       };

       fetch("/api/product",{
           method: "post",
           headers: {
               "Content-Type": "application/json"
           },
           body: JSON.stringify(obj)
       })
           .then(response => response.json())
           .then((result) => {
               console.log(result);
               loadProducts();
           })

    })
}