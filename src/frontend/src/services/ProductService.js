import axios from 'axios';

const api = axios.create({
    baseURL:'http://localhost:8080/api',
    withCredentials:false,
    headers:{
        Accept:'application/json',
        'Content-Type': 'application/json'
    }
})

export default{
    /*
    *  GET request
    *  $ curl -s -i -w "\n" -X GET localhost:8080/api/product/1
    */
    getProductById(id){
        return api.get(`/product/${id}`)
    },

    /*
     *  GET request
     *  $ curl -s -i -w "\n" -X GET localhost:8080/api/product/1/stock
     */
    getStockById(id){
        return api.get(`/product/${id}/stock`,13)

    },

    /*
   *  PUT request
   *  $ curl -s -i -w "\n" -X PUT localhost:8080/api/product/1/refill?amount=5
   */
    putRefillProduct(product,amount){
        return api.put(`/product/${product.id}/refill?amount=${amount}`)
    },

    /*
   *  PUT request
   *  $ curl -s -i -w "\n" -X PUT localhost:8080/api/product/1/buy?amount=3
   */
    putDecreaseProduct(product, amount){
        return api.put(`/product/${product.id}/buy?amount=${amount}`)
    }

}

// localhost:8080/api/product/1/refill?amount=5
