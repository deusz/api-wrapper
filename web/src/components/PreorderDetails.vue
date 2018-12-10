<template>

  <div>

    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">Preordery</el-breadcrumb-item>
      <el-breadcrumb-item>{{ preorder.name }}</el-breadcrumb-item>
    </el-breadcrumb>

    <hr />

    <h1>Preorder: {{ preorder.name }}</h1>

    <el-container class="container">
      <el-aside class="side-bar">
        <div><img v-bind:src="preorder.imageUrl" /></div>
        <div v-if="preorder.regularPrice">Regular price: {{ preorder.regularPrice }}</div>
        <div v-if="preorder.minPrice">Min price: {{ preorder.minPrice }}</div>
      </el-aside>

      <el-main>
        <div v-html="preorder.description">
        </div>
      </el-main>
    </el-container>

  </div>

</template>

<script>
  import axios from 'axios';
  import {
    Table, TableColumn, Breadcrumb, BreadcrumbItem, Container, Aside, Main, Header
  } from 'element-ui'

  export default {

    components: {
      'el-table': Table, 'el-table-column': TableColumn,
      'el-breadcrumb': Breadcrumb, 'el-breadcrumb-item': BreadcrumbItem,
      'el-container': Container, 'el-main': Main, 'el-aside': Aside, 'el-header': Header,
    },

    data() {
      return {
        entityId: this.$route.params.entityId,
        preorder: [],
        errors: []
      }
    },

    // Fetches posts when the component is created.
    created() {
      axios.get(`http://localhost:9080/api/v2/preorders/${this.entityId}`)
      .then(response => {
        this.preorder = response.data
      })
      .catch(e => {
        this.errors.push(e)
      })
    },
  }
</script>

<style>
  .container {
    height: 500px;
    border: 1px solid #eee;
  }
  .side-bar {
    background-color: rgb(238, 241, 246);
  }
  a {
    cursor: pointer
  }
  hr {
    border: 0;
    height: 1px;
    background: #333;
    background-image: linear-gradient(to right, #ccc, #333, #ccc);
  }
</style>
