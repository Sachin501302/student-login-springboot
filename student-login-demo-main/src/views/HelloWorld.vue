<template>
  <div class="hello-container">
    <h1>Hello World!</h1>
    <p>Congratulations, you have logged in successfully!</p>

    <div class="button-container">
      <button @click="goBack" class="btn">Return to login page</button>
      <button @click="openCourseApi" class="btn">Open Course API</button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

const goBack = () => {
  localStorage.clear();
  router.push("/");
};

const openCourseApi = () => {
  window.open("http://localhost:8080/api/courses", "_blank");
};
</script>

<style scoped>
.hello-container {
  padding: 40px;
  text-align: center;
}

.button-container {
  display: flex;
  gap: 15px;
  justify-content: center;
  padding: 20px 0;
}

.btn {
  padding: 8px 16px;
  cursor: pointer;
}
</style>
