import ProductService from "@/services/ProductService";

export default {
    /*
     *  GET request --> to fetch product by Id
     */
    fetchProductById({commit}, productId) {
        console.log('hhhhhhhhhhhhh')
        return ProductService.getProductById(productId)
            .then(({data}) => commit('SET_PRODUCT', data))
    },


    /*
     *  GET request --> to fetch Stock by Id
     */
    fetchStockById({commit}, stockId) {
        return ProductService.getStockById(stockId)
            .then(({data}) => commit('SET_STOCK', data))
    },


    /*
     *  PUT request --> to increase amount of a stock
     *  $ curl -s -i -w "\n" -X PUT localhost:8080/api/product/1/refill?amount=5
     */
    addStockAmount({commit, getters}, amount) {
        return ProductService.putRefillProduct(getters.getProduct, amount)
            .then(({data}) => commit('REFILL_PRODUCT', data))
    },


    /*
     *  PUT request --> to decrease amount of a stock
     *  $ curl -s -i -w "\n" -X PUT localhost:8080/api/product/1/buy?amount=3
     */
    decreaseStockAmount({commit, getters}, amount) {
        return ProductService.putDecreaseProduct(getters.getProduct, amount)
            .then(({data}) => commit('DECREASE_PRODUCT', data))
    }

}
