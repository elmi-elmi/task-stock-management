<template>
  <div
      class="no-search"

  >
    <p>Search {{page}}</p>
    <v-icon
        x-large
        color="green darken-2"
        class="rotate"
    >
      mdi-magnify
    </v-icon>
  </div>
</template>

<script>
export default {
  name: "NoSearchFallback",
  props:{
    page:{
      type:String,
      default:'Product'
    }
  }
}
</script>



<style lang="scss">

.no-search {
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  opacity: 50%;

}
.rotate:hover{
  transition: all 0.7s ease-out;
  transform:rotate(360deg) scale(1.2);
}


</style>

