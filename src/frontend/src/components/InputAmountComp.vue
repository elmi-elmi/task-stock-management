<template>
  <v-card
      class="d-flex justify-center align-center mb-2 " flat tile
  >
    <v-text-field
        class="mb-4"
        v-model="amount"
        label="Refill or Decrease Stock"
        light
        flat
        type="number"

    ></v-text-field>
    <v-btn
        ref="RefillBtn"
        :disabled="!amount"
        class="mx-2" fab dark small color="teal" @click="changeStock('refill')"
    >
      <v-icon dark>
        mdi-plus
      </v-icon>
    </v-btn>


    <v-btn
        ref="decreaseBtn"
        :disabled="!amount"
        class="mx-2" fab dark small color="pink" @click="changeStock('decrease')"
    >
      <v-icon dark>
        mdi-minus
      </v-icon>

    </v-btn>
  </v-card>
</template>

<script>


export default {
  name: "InputAmountComp",
  data() {
    return {
      amount: null
    }
  },

  methods: {
    changeStock(req) {
      const requestToStore = req === 'refill'
          ? 'product/addStockAmount'
          : 'product/decreaseStockAmount'

      const id = this.$route.name === 'product'
      ?this.$store.getters['product/getProduct'].id
      :this.$store.getters['product/getStock'].id

      try {
        this.$store.dispatch(requestToStore, {name:this.$route.name,id,amount:this.amount})
            .then(() => {
              this.amount = null;
            }).catch((e) => {
          this.$router.push({name: 'notFound'})
        })
      } catch (e) {
        console.log('there is a problem in refill')

      }
    },
  },
}
</script>

<style scoped>

</style>
