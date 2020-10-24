new Vue({
  el: '#app',
  data: {
    books: []
  },
  mounted: function () {
    axios.get('/api/book')
      .then(response => this.books = response.data)
      .catch(response => console.log(response))
  }
})