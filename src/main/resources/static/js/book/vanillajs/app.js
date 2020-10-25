(function () {

    function Model() {
        let self = this;
        this.book;

        // observer pattern
        this.observers = [];

        this.registerObserver = function (observer) {
            self.observers.push(observer);
        }

        this.notifyAll = function () {
            self.observers.forEach(function (observer) {
                observer.update(self);
            })
        }

        Object.defineProperty(this, "books", {
            get: function () { return books; },
            set: function (value) {
                books = value;
                this.notifyAll();
            }
        });

        // fetch
        this.fetch = function () {
            axios.get('/api/book')
                .then(response => self.books = response.data)
                .catch(response => console.log(response))
        }
    }

    function View() {

        this.elemTblBody = document.getElementById('table-body');

        this.template = "<td>{{book.isbn}}</td><td>{{book.title}}</td><td>{{book.author}}</td><td>{{book.releaseDate}}</td>"

        this.renderTblBody = function (books) {

            books.map(book => {
                const elemTr = document.createElement("tr");
                const compiled = this.template.replace('{{book.isbn}}', book.isbn).replace('{{book.title}}', book.title).replace('{{book.author}}', book.author).replace('{{book.releaseDate}}', book.releaseDate);
                elemTr.innerHTML = compiled;
                this.elemTblBody.appendChild(elemTr);
            });
        }

        this.update = function (model) {
            this.renderTblBody(model.books);
        }

    }

    function Controller(model, view) {
        this.model = model;
        model.registerObserver(view);

        this.bindEvents = function () {

        }

        // call action by hash 
        this.dispatch = function (hash) {

            console.log(hash);

        }

        // action
        this.index = function () {
            this.model.fetch();
        }


    }

    function App() {
        this.model = new Model();
        this.view = new View();
        this.controller = new Controller(this.model, this.view);
    }

    const app = new App();

    // as Mounted
    window.addEventListener('load', (event) => {
        app.controller.index();
    });

    // as Router
    window.addEventListener('hashchange', (event) => {
        app.controller.dispatch(document.location.hash);
    });

}());