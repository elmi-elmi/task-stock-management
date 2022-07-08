export default  {
    /*
     *  requested product save in the `state`
     */
    SET_PRODUCT(state, product) {
        state.product = product
    },


    /*
     *  requested stock save in the `state`
     */
    SET_STOCK(state, stock){
        state.stock = stock
    },


    /*
     *  update amount of stock in the `state`
     */
    REFILL_PRODUCT(state, product){
        state.product = product
    },


    /*
     *  update amount of stock in the `state`
     */
    DECREASE_PRODUCT(state,product){
        state.product = product
    }


    // Todo => change to UPDATE_PRODUCT Or just update amount
}
