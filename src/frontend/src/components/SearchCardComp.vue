<template>
  <v-card
      class="mx-auto pa-4 d-flex align-center  "
      max-width="400px"
      min-height="120px"
      outlined
      elevation="18"
  >

    <v-text-field
        autofocus
        class="mb-2"
        :value="value"
        @input="handelInput"
        :label="label"
        light
        flat
        clearable
        clear-icon="mdi-close-circle-outline"
        @keyup.enter="buttonSearchHandling"

    ></v-text-field>

    <v-btn
        :disabled="!value"
        class="mx-2" fab dark small color="teal"
        @click="buttonSearchHandling"

    >
      <v-icon light>
        mdi-magnify
      </v-icon>
    </v-btn>

  </v-card>
</template>

<script>
export default {
  name: "SearchCardComp",
  props:{
    value:{
      type:String,
      default:null
    },
    label:{
      type:String,
      default:'Enter Id'
    }
  },
  emits:['input','search'],
  methods:{
    buttonSearchHandling(){
      this.$emit('search')
    },
    handelInput(e){
      this.$emit('input',e)
    }
  }
}
</script>

<style scoped>

</style>
