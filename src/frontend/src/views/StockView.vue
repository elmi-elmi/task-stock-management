<template>
  <div>
    <v-expand-transition
    >
      <v-card
          v-show="expand || stock.id"
          class=" mx-auto  mb-1 "
          width="400px"
          height="300px"
          outlined elevation="18"
      >
        <NoSearchFallbackComp v-if="!stock.id" page="Stock"/>
        <DisplayResultComp :stock-tween="Math.trunc(tweenStock || stock.value)"  :result="stock" v-else title="Stock"/>
        <SellAndBuyComp v-if="stock.id"/>

      </v-card>
    </v-expand-transition>
    <SearchByIdComp v-model="expand" label="Search Stock By Id"/>

    <v-snackbar
        v-model="snackbar"
        :timeout="timeout"
        :color="colorSnackbar"
        class=""
    >
      {{ messageSnack }}

    </v-snackbar>

  </div>
</template>

<script>

import NoSearchFallbackComp from "@/components/NoSearchFallbackComp";
import DisplayResultComp from "@/components/DisplayResultComp";
import SearchByIdComp from "@/components/request/SearchByIdComp";
import SellAndBuyComp from "@/components/request/SellAndBuyComp";
import {gsap} from "gsap";


export default {
  components: {SearchByIdComp, NoSearchFallbackComp, DisplayResultComp, SellAndBuyComp},
  computed: {
    stock() {
      return this.$store.getters['product/getStock']
    },


  },
  data() {
    return {
      expand: false,
      colorSnackbar: 'success',
      snackbar: false,
      timeout: 2000,
      messageSnack: '',
      tweenStock: this.stock.value || 0

    }
  },
  // tween
  watch: {
    'stock.value':function(newVal, preVal){
      gsap.to(this.$data,{
        duration:0.5,
        ease:'ease-out',
        tweenStock:newVal
      })
    },
    // watch stock to show snackbar product
    stock: function (newVal, preVal) {
      if (!this.stock.id) {
        // the stock has been removed
        this.colorSnackbar = 'primary';
        this.messageSnack = 'Successfully removed the stock';

      } else if (newVal.value === preVal.value) {
        // The search for a new stock has been completed
        this.messageSnack = 'Successfully added the stock';
        this.colorSnackbar = 'success'
      } else {
        //  The stock amount has been changed
        this.messageSnack = 'Successfully changed stock';
        this.colorSnackbar = 'success'
      }

      this.snackbar = true

    }
  }

}
</script>
