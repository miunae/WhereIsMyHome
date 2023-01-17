<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label="작성자:" label-for="userid" description="작성자를 입력하세요.">
          <b-form-input
            id="userid"
            :disabled="isUserid"
            v-model="localqna.userid"
            type="text"
            required
            placeholder="작성자 입력..."
            ref="userid"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label="제목:" label-for="subject" description="제목을 입력하세요.">
          <b-form-input
            id="subject"
            v-model="localqna.subject"
            type="text"
            required
            placeholder="제목 입력..."
            ref="subject"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="localqna.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
            ref="content"
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary" class="m-1" v-if="this.type === 'register'">글작성</b-button>
        <b-button type="submit" variant="primary" class="m-1" v-else>글수정</b-button>
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
// import http from "@/api/http";
import { mapState, mapActions } from "vuex";
const qnaboardStore = "qnaboardStore";

export default {
  name: "qnaInputItem",
  data() {
    return {
      localqna: {
        qnano: 0,
        userid: "",
        regtime: "",
        content: "",
        subject: "",
        hit: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      this.localqna.qnano =this.qna.qnano;
      this.localqna.userid =this.qna.userid;
      this.localqna.regtime =this.qna.regtime;
      this.localqna.content =this.qna.content;
      this.localqna.subject =this.qna.subject;
      this.localqna.hit =this.qna.hit;
      this.isUserid = true;
    }
  },
  computed : {
    ...mapState(qnaboardStore, ["qna"]),
  },
  methods: {
    ...mapActions(qnaboardStore, ["createQna", "modifyQna"]),
    onSubmit(event) {
      event.preventDefault();
      console.log("event : ", this.event);
      let err = true;
      let msg = "";
      !this.localqna.userid && ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userid.focus());
      err && !this.localqna.subject && ((msg = "제목 입력해주세요"), (err = false), this.$refs.subject.focus());
      err && !this.localqna.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.createqna() : this.modifyqna();
    },
    onReset(event) {
      event.preventDefault();
      this.localqna.qnano = 0;
      this.localqna.subject = "";
      this.localqna.content = "";
    },
    createqna() {
      console.log(this.localqna);
      this.createQna(this.localqna);
      this.$router.push({name:"qnalist"});
    },
    modifyqna() {
      this.modifyQna(this.localqna);
      this.$router.push({name:"qnalist"});
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>

<style></style>
