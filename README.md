# Request scoped component dependencies

An example project that presents request scoped component dependencies.

Project based on Friboo library.

## Development

Run  the application:

```
$ lein repl
user=> (reset)
```

For REPL-driven interactive development configuration variables can be provided in `dev-config.edn` file, which will be read on each system restart.

## Testing

```
$ lein test
```

## Building

```
$ lein uberjar
```

## Running

```
$ lein run
```

The following configuration environment variables are available:

| Variable | Meaning | Default | Example |
|---|---|---|---|
| API_EXAMPLE_PARAM | Example parameter with `:api-` prefix | `bar` | `foo` |

## License

Copyright © 2018 Oskari Porkka

Distributed under the MIT License.
