<template>
  <div>

    <v-expand-transition

    >
      <v-card
          v-show="expand || product.id"
          class=" mx-auto  mb-1"
          width="400px"
          height="300px"
          outlined elevation="18"
      >

        <NoSearchFallbackComp v-if="!product.name"/>
        <DisplayResultComp v-else :result="product" title="Product"/>
        <SellAndBuyComp v-if="product.name"/>

      </v-card>
    </v-expand-transition>

    <SearchByIdComp v-model="expand" label="Search Product By Id"/>

    <div class="text-center d-flex mx-auto">

      <v-snackbar
          v-model="snackbar"
          :timeout="timeout"
          :color="colorSnackbar"
          class=""
      >
        {{ messageSnack }}

      </v-snackbar>
    </div>


  </div>

</template>

<script>

import SearchByIdComp from "@/components/request/SearchByIdComp";
import SellAndBuyComp from "@/components/request/SellAndBuyComp";
import NoSearchFallbackComp from "@/components/NoSearchFallbackComp";
import DisplayResultComp from "@/components/DisplayResultComp";
import {gsap} from 'gsap'

export default {
  name: 'ProductView',
  components: {SearchByIdComp, SellAndBuyComp, NoSearchFallbackComp, DisplayResultComp},
  data() {
    return {
      expand: false,
      colorSnackbar: 'success',
      snackbar: false,
      timeout: 2000,
      messageSnack: '', tweennum: 0
    }

  },

  computed: {
    product() {
      return this.$store.getters['product/getProduct']
    },
    stock() {
      return this.$store.getters['product/getProduct'].stock
    }
  },
  watch: {

    // watch product to show snackbar
    product: function (newVal, preVal) {
      if (!this.product.id) {
        // the product has been removed
        this.colorSnackbar = 'primary';
        this.messageSnack = 'Successfully removed the product';

      } else if (newVal.stock === preVal.stock) {
        // The search for a new product has been completed
        this.messageSnack = 'Successfully added the product';
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
