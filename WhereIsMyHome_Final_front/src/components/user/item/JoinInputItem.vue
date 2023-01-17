<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit">
        <b-form-group id="userid-group" label="아이디:" label-for="userid" description="사용할 아이디를 입력하세요.">
          <b-form-input
            id="userid"
            v-model="user.userid"
            type="text"
            required
            ref="userid"
            placeholder="아이디 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label="비밀번호:" label-for="userpwd" description="사용할 비밀번호를 입력하세요.">
          <b-form-input
            id="userpwd"
            v-model="user.userpwd"
            type="text"
            required
            ref="userpwd"
            placeholder="비밀번호 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="이름:" label-for="username">
          <b-form-input
            id="username"
            v-model="user.username"
            placeholder="이름 입력..."
            required
            rows="10"
            ref="username"
            max-rows="15"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="이메일:" label-for="email">
          <b-form-input
            id="email"
            v-model="user.email"
            required
            placeholder="이메일 입력..."
            rows="10"
            ref="email"
            max-rows="15"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="등급:" label-for="grade">
          <b-form-select v-model="user.grade" :options="options"></b-form-select>
        </b-form-group>

        <b-button type="submit" variant="primary" class="m-1">회원 가입</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { registUser } from "@/api/member";

export default {
  name: "JoinInputItem",
  data() {
    return {
      user:{
        userid:"",
        userpwd:"",
        username:"",
        email:"",
        grade:"null",
      },
      options: [
        { value: null, text: '회원 등급 선택' },
        { value: 'A', text: 'A' },
        { value: 'B', text: 'B' },
        { value: 'C', text: 'C' },
      ],
    };
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      console.log(" gd");
      this.join();
      
      //else this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    
    join() {
      let param = {
        userid: this.user.userid,
        userpwd: this.user.userpwd,
        username: this.user.username,
        email: this.user.email,
        grade: this.user.grade,
        // user:[userid, userpwd, username, email],
      };
      registUser(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveLogin();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveLogin() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style></style>
