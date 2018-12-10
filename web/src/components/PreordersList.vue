<template>

  <div>

    <el-breadcrumb separator="/">
      <el-breadcrumb-item>Preordery</el-breadcrumb-item>
    </el-breadcrumb>

    <hr />

    <el-table :data="preorders" style="width: 100%">
      <div slot="empty">
        Brak danych
      </div>

      <el-table-column width="100">
        <template slot-scope="props">
          <img v-bind:src="props.row.imageUrl" />
        </template>
      </el-table-column>

      <el-table-column label="Nazwa" width="400">
        <template slot-scope="props">
          <router-link :to="{ name: 'preorder-get', params: { entityId: props.row.entityId }}">{{ props.row.name }}</router-link>
          <!--<a v-bind:href="?accountId=''+props.row.accountId+''">{{ props.row.accountId }}</a>-->
        </template>
      </el-table-column>

      <el-table-column prop="regularPrice" label="Cena" width="100">
      </el-table-column>
      <el-table-column prop="minPrice" label="Cena minimalna" width="100">
      </el-table-column>

    </el-table>

  </div>



</template>

<script>
  import axios from 'axios';
  import {
    Table, TableColumn, Breadcrumb, BreadcrumbItem
  } from 'element-ui'

  export default {

    components: {
      'el-table': Table, 'el-table-column': TableColumn,
      'el-breadcrumb': Breadcrumb, 'el-breadcrumb-item': BreadcrumbItem },

    data() {
      return {
        preorders: [],
        errors: []
      }
    },

    created() {
      axios.get(`http://localhost:9080/api/v2/preorders`)
        .then(response => {
          console.log(response.data);
          this.preorders = response.data._embedded.preorderResources
        })
        .catch(e => {
          this.preorders.push(e)
        })
    }
  }
</script>

<style>
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
