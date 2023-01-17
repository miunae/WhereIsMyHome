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
            readonly
          ></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label="비밀번호:" label-for="userpwd" description="수정할 비밀번호를 입력하세요.">
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

        <b-button type="submit" variant="primary" class="m-1">내정보 수정</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { modifyUser, getUser } from "@/api/member";

export default {
  name: "UserInputItem",
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

  created() {
    let param = this.$route.params.userid;
    console.log(param);
      getUser(
        param,
        ({ data }) => {
          this.user = data;
        },
        (error) => {
          console.log(error);
        } 
      );
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      console.log(" gd");
      this.modify();
      this.moveMypage();
      
      //else this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    
    modify() {
      let param = {
        userid: this.user.userid,
        userpwd: this.user.userpwd,
        username: this.user.username,
        email: this.user.email,
        grade: this.user.grade,
        // user:[userid, userpwd, username, email],
      };
      modifyUser(
        param,
        ({ data }) => {
          let msg = "변경 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "변경이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "mypage" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>
