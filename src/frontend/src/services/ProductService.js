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
    getProductById(id){
        return api.get(`/product/${id}`)
    },
    getStockById(id){
        return api.get(`/product/${id}/stock`,13)

    },
    putRefillProduct(product,amount){
        return api.put(`/product/${product.id}/refill?amount=${amount}`)
    }

}

// localhost:8080/api/product/1/refill?amount=5
