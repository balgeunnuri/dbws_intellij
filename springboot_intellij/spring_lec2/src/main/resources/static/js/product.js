window.onload = function() { //onload: 페이지 로드가 끝났을 때?

    loadProducts();
    insertProduct();
    deleteHandler();
    updateProduct();

}
// 제이쿼리, 윈도우

function updateProduct() {
    const updateBtn = document.querySelector("#updateBtn");
    const selectEl = document.querySelector("select[name='p_no']");
    const nameEl = document.querySelector("#up-name");
    const priceEl = document.querySelector("#up-price");

    updateBtn.addEventListener("click", function() {

    const obj = {
        p_no : selectEl.value,
        p_name : nameEl.value,
        p_price : priceEl.value
    }
        fetch("/api/product", {
            method : "put",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(obj)
        }).then(response => response.text())
            .then(result => {
                console.log(result);
                loadProducts();
                nameEl.value = "";
                priceEl.value = "";
            })
    })

}

function deleteHandler() {
    const delBtn = document.querySelector("#delBtn");
    delBtn.addEventListener("click", () => {
        const pk = document.querySelector("input[name='pk']");
        deleteProduct(pk.value);
        pk.value = "";
    })
}

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

                item.querySelector(".no").dataset.no = product.p_no;
                item.querySelector(".no").dataset.name = product.p_name;
                item.querySelector(".no").dataset.price = product.p_price;

                item.classList.remove("temp");
                productList.appendChild(item);

                item.lastElementChild.addEventListener("click", () => {
                    console.log(product.p_no);
                    deleteProduct(product.p_no);
                })
            })
            modal()

            // option 태그 생성
            const noEl = document.querySelectorAll(".no");
            const selectEl = document.querySelector("select[name='p_no']");

            let optionEl;
            noEl.forEach(noDiv => {
                let no = noDiv.innerText;
                optionEl += `<option value="${no}">no. ${no}</option>`;
            })
            selectEl.innerHTML = optionEl; // 여기서 innerText 사용 안ㄷ ㅚㅁ. 두개 차이?


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

function modal(){
    const openModalBtn = document.getElementById("openModal");
    const closeModalBtn = document.getElementById("closeModal");
    const myModal = document.getElementById("myModal");

        const openModalNo = document.querySelectorAll(".no")
        console.log(openModalNo);

        openModalNo.forEach(noEl => {
            noEl.addEventListener("click", (e) => {
                myModal.showModal();
                console.log(e.target.dataset);
                console.log(e.target.dataset.no);
                console.log(e.target.dataset.name);
                console.log(e.target.dataset.price);
                document.querySelector(".modal-no").innerText = e.target.dataset.no;
                document.querySelector(".modal-name").innerText = e.target.dataset.name;
                document.querySelector(".modal-price").innerText = e.target.dataset.price;
            })
        })

        openModalBtn.addEventListener("click", () => {
            myModal.showModal();
        })

        closeModalBtn.addEventListener("click", () => {
            myModal.close();
        })
}