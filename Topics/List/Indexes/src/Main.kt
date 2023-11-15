fun solution(products: List<String>, product: String) {
    // put your code here
    products.forEachIndexed { index, element ->
        if(element == product) print("$index ")
    }
}