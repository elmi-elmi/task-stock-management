import Vue from 'vue'
import Vuex from 'vuex'
import ProductService from "@/services/ProductService";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        product: {},
        stock: null
    },
    getters: {
        getProduct: state => state.product,
        getStock: state => state.stock
    },
    mutations: {
        SET_PRODUCT(state, product) {
            state.product = product
        },
        SET_STOCK(state, stock){
            state.stock = stock
        },
        REFILL_PRODUCT(state, product){
            state.product = product
        },
        DECREASE_PRODUCT(state,product){
            state.product = product
        }
        // Todo => change to UPDATE_PRODUCT
    },
    actions: {
        fetchProductById({commit}, productId) {
            return ProductService.getProductById(productId)
                .then(({data}) => commit('SET_PRODUCT', data))
        },
        fetchStockById({commit}, stockId){
            return ProductService.getStockById(stockId)
                .then(({data})=>commit('SET_STOCK',data))
        },
        addStockAmount({commit, getters}, amount){
            return ProductService.putRefillProduct(getters.getProduct, amount)
                .then(({data})=>commit('REFILL_PRODUCT',data))
        },
        decreaseStockAmount({commit, getters},amount){
            return ProductService.putDecreaseProduct(getters.getProduct,amount)
                .then(({data})=>commit('DECREASE_PRODUCT',data))
        }


    },
    modules: {}
})
