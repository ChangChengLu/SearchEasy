<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
#app {
  margin: 0 auto;
  padding: 20px;
  max-width: 1024px; /*最⼤间距*/
}
nav {
  padding: 30px;
}
nav a {
  font-weight: bold;
  color: #2c3e50;
}
nav a.router-link-exact-active {
  color: #42b983;
}
</style>
