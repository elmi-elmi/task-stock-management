<template>
  <div>
    <h1>{{ title }}</h1>

    <button @click="show">product</button>
    <hr>
    <button @click="showStock">stock</button>
    <hr>
    <button @click="refill">refill</button>
    <hr>
    <button @click="decreaseAmount">decrease</button>
  </div>
</template>

<script>
import ProductService from "@/services/ProductService";

export default {
  name: 'Home',
  data() {
    return {title: 'sdfsd'}
  },
  mounted() {

  },
  methods: {
    show() {
      try {
        this.$store.dispatch('fetchProductById', 2)
            .then(() => {
              this.title = this.$store.getters.getProduct
            })
      } catch (e) {
        console.log('there is a problem')
      }
    },
    showStock(){
      try {
        this.$store.dispatch('fetchStockById',10)
        .then(()=>{
          this.title = this.$store.getters.getStock
        })
      }catch (e){
        console.log('there is a problem in stock')
      }
    },
    refill(){
      try{
        this.$store.dispatch('addStockAmount',13)
        .then(()=>{
          this.title = this.$store.getters.getProduct
        })
      }catch(e){
        console.log('there is a problem in refill')

      }
    },
  decreaseAmount(){
    try{
      this.$store.dispatch('decreaseStockAmount', 15).then(()=>{
        this.title = this.$store.getters.getProduct
      })
    }catch (e) {
      console.log('decrease amount problem')
    }
  }
  },


}
</script>
