class BookApp extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			books: []
		};
	}

	componentDidMount() {
		axios.get(`/api/book`)
			.then(response => {
				const books = response.data;
				this.setState({ books });
			}).catch(response => console.log(response));
	}

	render() {
		return (
			<React.Fragment>
				<h1>Book Management</h1>
				<table className="table table-striped">
					<thead>
						<tr>
							<th>isbn</th>
							<th>Title</th>
							<th>Author</th>
							<th>Release Date</th>
						</tr>
					</thead>
					<tbody>
						{this.state.books.map(book =>
							<tr key={book.bookId}>
								<td>{book.isbn}</td>
								<td>{book.title}</td>
								<td>{book.author}</td>
								<td>{book.releaseDate}</td>
							</tr>
						)
						}

					</tbody>
				</table>
			</React.Fragment>
		)
	}
}

ReactDOM.render(
	<BookApp />,
	document.getElementById('root')
);