<template>
  <v-card class="d-flex justify-center align-center mb-2 pa-4" flat tile>
    <v-text-field
      class="mb-2"
      v-model="amount"
      label="Refill or Decrease Stock"
      light
      flat
      type="number"
    ></v-text-field>
    <v-btn
      ref="RefillBtn"
      :disabled="!amount"
      class="mx-2"
      fab
      dark
      small
      color="teal"
      @click="changeStockValue('refill')"
    >
      <v-icon dark> mdi-plus </v-icon>
    </v-btn>

    <v-btn
      ref="decreaseBtn"
      :disabled="!amount"
      class="mx-2"
      fab
      dark
      small
      color="pink"
      @click="changeStockValue('decrease')"
    >
      <v-icon dark> mdi-minus </v-icon>
    </v-btn>
  </v-card>
</template>

<script>
export default {
  name: "InputAmountComp",
  data() {
    return {
      amount: null,
    };
  },

  methods: {
    // dispatch action -- to increase or decrease amount of the stock
    changeStockValue(req) {
      // select which apis should be sent
      const requestToStore =
        req === "refill"
          ? "product/addStockAmount"
          : "product/decreaseStockAmount";

      // get id
      const id =
        this.$route.name === "product"
          ? this.$store.getters["product/getProduct"].id
          : this.$store.getters["product/getStock"].id;

      // send request to store
      this.$store
        .dispatch(requestToStore, {
          name: this.$route.name,
          id,
          amount: this.amount,
        })
        .then(() => (this.amount = null)) // clear input
        .catch((e) => {
          // Todo -- check status error
          let name = "404Resource";
          // const status = e.response.status

          if (e.code === "ERR_NETWORK") name = "networkError";
          this.$router.push({
            name,
            params: { message: e.message, res: e.response.data },
          });
        });
    },
  },
};
</script>

<style scoped></style>
