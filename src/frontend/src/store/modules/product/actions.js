import ProductService from "@/services/ProductService";

export default {
    /*=======================================================
        REQUESTS TO BACKEND
     =======================================================*/
    /*
     *  GET request --> to fetch product by ID
     */
    fetchProductById({commit}, productId) {
        return ProductService.getProductById(productId)
            .then(({data}) => commit('SET_PRODUCT', data))
    },


    /*
     *  GET request --> to fetch Stock by ID
     */
    fetchStockById({commit}, stockId) {
        return ProductService.getStockById(stockId)
            .then(({data}) => commit('SET_STOCK', {data, stockId}))
    },


    /*
     *  PUT request --> to increase amount of a stock
     */
    addStockAmount({commit}, {name,id,amount}) {
        // IF REQUEST FROM PRODUCT ROUTE
        if (name === 'product') return ProductService.putRefillProduct(id,amount)
            .then(({data}) => commit('REFILL_PRODUCT', data))

        // IF REQUEST FROM STOCK ROUTE
        return ProductService.putRefillProduct(id, amount)
            .then(({data}) => commit('REFILL_STOCK', data))
    },


    /*
     *  PUT request --> to decrease amount of a stock
     */
    decreaseStockAmount({commit}, {name,id,amount}) {
        // IF REQUEST FROM PRODUCT ROUTE
        if (name === 'product') return ProductService.putDecreaseProduct(id, amount)
            .then(({data}) => commit('DECREASE_PRODUCT', data))

        // IF REQUEST FROM STOCK ROUTE
        return ProductService.putDecreaseProduct(id, amount)
            .then(({data}) => commit('DECREASE_STOCK', data))
    }

}
