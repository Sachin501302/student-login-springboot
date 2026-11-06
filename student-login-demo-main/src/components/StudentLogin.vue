
<template>
  <div class="login-container">
    <div class="edit-form">
      <h2>Student Login Portal</h2>

      <form @submit.prevent="login">
        <label for="studentId">Student ID</label>
        <input
          id="studentId"
          v-model="studentLoginRequest.studentId"
          required
        />

        <label for="password">Password</label>
        <input
          id="password"
          type="password"
          v-model="studentLoginRequest.password"
          required
        />

        <button type="submit">Login</button>
        <button type="button" @click="showModal = true">
          Create Account
        </button>
      </form>

      <p v-if="message" class="message" :class="messageIsError ? 'error' : 'ok'">{{ message }}</p>
    </div>

    <div v-if="showModal" class="modal-overlay">
      <div class="modal-card">
        <h2>Create Student Account</h2>

        <form @submit.prevent="submitRegistration">
          <label for="newStudentId">Student ID</label>
          <input id="newStudentId" v-model="newStudent.studentId" required />

          <label for="newPassword">Password</label>
          <input
            id="newPassword"
            type="password"
            v-model="newStudent.password"
            required
          />

          <label for="studentName">Full Name</label>
          <input id="studentName" v-model="newStudent.name" required />

          <label for="studentEmail">Email</label>
          <input
            id="studentEmail"
            type="email"
            v-model="newStudent.email"
            required
          />

          <button type="submit">Submit</button>
          <button type="button" @click="closeModal">Cancel</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import LoginService from "../services/LoginService";

export default {
  name: "StudentLogin",

  data() {
    return {
      studentLoginRequest: {
        studentId: "",
        password: ""
      },
      message: "",
      messageIsError: false,
      showModal: false,
      newStudent: {
        studentId: "",
        password: "",
        name: "",
        email: ""
      }
    };
  },

  methods: {
    login() {
      this.message = "";
      LoginService.login(this.studentLoginRequest)
        .then((response) => {
          const student = response.data;
          localStorage.setItem("sid", student.id);
          this.messageIsError = false;
          this.message = `Welcome, ${student.name}.`;
          alert("success!");
          this.$router.push("/helloworld");
        })
        .catch((error) => {
          this.studentLoginRequest.studentId = "";
          this.studentLoginRequest.password = "";
          this.messageIsError = true;
          this.message =
            error.response?.data?.message ||
            "Login failed, check internet or credential";
        });
    },

    submitRegistration() {
      LoginService.register(this.newStudent)
        .then((response) => {
          const successMsg =
            response.data?.message || "Registration submitted successfully!";
          this.messageIsError = false;
          this.message = successMsg;
          alert(successMsg);
          this.closeModal();
        })
        .catch((error) => {
          const errorMsg =
            error.response?.data?.message ||
            "Registration failed, please check backend connection.";
          this.messageIsError = true;
          this.message = errorMsg;
          alert(errorMsg);
        });
    },

    closeModal() {
      this.showModal = false;
      this.newStudent = {
        studentId: "",
        password: "",
        name: "",
        email: ""
      };
    }
  },

  mounted() {
    this.message = "";
  }
};
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #eaf2f8;
  padding: 20px;
}

.edit-form,
.modal-card {
  width: 100%;
  max-width: 360px;
  padding: 25px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
}

label {
  display: block;
  margin-top: 12px;
  margin-bottom: 5px;
}

input {
  box-sizing: border-box;
  width: 100%;
  padding: 9px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  margin-top: 15px;
  margin-right: 8px;
  padding: 9px 12px;
  cursor: pointer;
}

.message {
  margin-top: 12px;
}

.message.error {
  color: #b00020;
}

.message.ok {
  color: #1b7f3a;
}

.modal-overlay {
  position: fixed;
  inset: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  background: rgba(0, 0, 0, 0.5);
}
</style>
