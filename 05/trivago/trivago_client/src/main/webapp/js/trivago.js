
function changePrice() {
    const x = document.getElementById("price");
    const y = document.getElementById("price-with-extra");
    if(y.style.display === "none") {
        x.style.display = "none";
        y.style.display = "block";
    } else {
        x.style.display = "block";
        y.style.display = "none";
    }
}