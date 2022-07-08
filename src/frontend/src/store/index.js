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
        SET_STOCK(state, stock) {
            state.stock = stock
        },
        REFILL_PRODUCT(state, product) {
            state.product = product
        }
    },
    actions: {
        fetchProductById({commit}, productId) {
            return ProductService.getProductById(productId)
                .then(({data}) => commit('SET_PRODUCT', data))
        },


    },
    modules: {}
})
