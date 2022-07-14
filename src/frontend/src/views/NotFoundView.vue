<template>
  <div>
    <div v-if="$route.name === '404Resource'">
      <h1>{{ $route.params.res.error || $route.params.res }}</h1>
      <p>{{ $route.params.message }}</p>
    </div>
    <div v-else>
      <h1>This page not found</h1>
    </div>

    <p>
      Back to
      <router-link :to="{ name: 'product' }">Home</router-link>
    </p>
  </div>
</template>

<script>
export default {
  name: "NotFoundView",
};
</script>

<style scoped></style>
