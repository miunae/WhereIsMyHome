<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>내정보 수정</h3></b-alert>
      </b-col>
    </b-row>
    <user-input-item/>
  </b-container>
</template>

<script>
import UserInputItem from "@/components/user/item/UserInputItem";

export default {
  name: "UserModify",
  components: {
    UserInputItem,
  },
};
</script>

<style></style>
