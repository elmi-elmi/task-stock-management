<template>
  <div>
    <v-card
        class="d-flex align-center flex-column mx-auto pa-4 mb-1"
        max-width="400px"
        min-height="300px"
        outlined
        elevation="18"
    >

      <v-list-item three-line class="mb-12"
      >
        <v-list-item-content>

          <v-card
              class="d-flex align-center justify-space-between mb-2"
              flat
              tile
          >
            <div class="text-overline ">
              Product
            </div>
            <div class="text-overline ">
              {{ $store.getters['product/getProduct'].id }}
            </div>
          </v-card>

          <v-list-item-title class="text-h5 mb-1">
            {{ $store.getters['product/getProduct'].name }}
          </v-list-item-title>

          <v-list-item-subtitle>
            {{ $store.getters['product/getProduct'].stock }}
          </v-list-item-subtitle>

        </v-list-item-content>

      </v-list-item>

      <InputAmountComp v-if="product.name"  />


      <div
          v-else
          class="searchProduct-something"
      >
        <p>Search Products</p>
        <v-icon
            x-large
            color="green darken-2"
        >
          mdi-magnify
        </v-icon>
      </div>

    </v-card>


    <SearchCardComp v-model="id" @search="searchProduct"/>


  </div>

</template>

<script>

import SearchCardComp from "@/components/SearchCardComp";
import InputAmountComp from "@/components/InputAmountComp";

export default {
  name: 'ProductView',
  components: {SearchCardComp, InputAmountComp},
  data() {
    return {id: null, amount: null}
  },
  computed: {
    product() {
      return this.$store.getters['product/getProduct']
    }
  },
  methods: {
    searchProduct() {
      try {
        this.$store.dispatch('product/fetchProductById', this.id)
            .then(() => {
              this.id = null
              this.amount = null;
            }).catch((e) => {
          this.$router.push({name: 'notFound'})
        })


      } catch (e) {
        console.log('there is a problem (500)')

      }
    },
    showStock() {
      try {
        this.$store.dispatch('product/fetchStockById', 10)
            .then(() => {
              this.product = this.$store.getters['product/getStock']
            })
            .catch((e) => {
              console.log('---- 404')
              console.log(e)
            })
      } catch (e) {
        console.log('there is a problem in stock (500)')
        console.log(e)
      }
    },
    //   refill() {
    //     try {
    //       this.$store.dispatch('product/addStockAmount', this.amount)
    //           .then(() => {
    //             this.amount = null;
    //           })
    //     } catch (e) {
    //       console.log('there is a problem in refill')
    //
    //     }
    //   },
    //   decreaseAmount() {
    //     try {
    //       this.$store.dispatch('product/decreaseStockAmount', this.amount).then(() => {
    //         this.amount = null;
    //
    //       })
    //     } catch (e) {
    //       console.log('decrease amount problem')
    //     }
    //   }
  },


}
</script>


<style lang="scss">

.searchProduct-something {
  text-align: center;
  position: absolute;
  top: 70%;
  left: 50%;
  transform: translate(-50%, -50%);
  opacity: 50%;
}


</style>
