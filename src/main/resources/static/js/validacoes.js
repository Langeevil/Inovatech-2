(() => {
    const regras = {
        cpf: {
            pattern: "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            inputmode: "numeric",
            maxlength: "14",
            title: "Informe um CPF valido. Ex: 000.000.000-00 ou 00000000000"
        },
        telefone: {
            pattern: "^(\\(\\d{2}\\)\\s?|\\d{2}\\s?)\\d{4,5}-?\\d{4}$",
            inputmode: "tel",
            maxlength: "15",
            title: "Informe um telefone valido. Ex: (11) 99999-9999 ou 11999999999"
        },
        dinheiro: {
            pattern: "^\\d+(\\.\\d{2})$",
            type: "text",
            inputmode: "decimal",
            title: "Informe um valor em reais. Ex: 99.90"
        },
        inteiro: {
            inputmode: "numeric",
            min: "1",
            step: "1",
            title: "Informe um numero inteiro maior que zero"
        }
    };

    const somenteDigitos = (valor) => valor.replace(/\D/g, "");

    const formatarCpf = (valor) => {
        const digitos = somenteDigitos(valor).slice(0, 11);

        return digitos
            .replace(/^(\d{3})(\d)/, "$1.$2")
            .replace(/^(\d{3})\.(\d{3})(\d)/, "$1.$2.$3")
            .replace(/^(\d{3})\.(\d{3})\.(\d{3})(\d)/, "$1.$2.$3-$4");
    };

    const formatarTelefone = (valor) => {
        const digitos = somenteDigitos(valor).slice(0, 11);

        if (digitos.length <= 2) {
            return digitos;
        }

        if (digitos.length <= 6) {
            return digitos.replace(/^(\d{2})(\d+)/, "($1) $2");
        }

        if (digitos.length <= 10) {
            return digitos.replace(/^(\d{2})(\d{4})(\d+)/, "($1) $2-$3");
        }

        return digitos.replace(/^(\d{2})(\d{5})(\d{1,4}).*/, "($1) $2-$3");
    };

    const formatarDinheiro = (valor, finalizar = false) => {
        const normalizado = valor
            .replace(",", ".")
            .replace(/[^\d.]/g, "");

        const partes = normalizado.split(".");
        const temDecimal = partes.length > 1;
        const inteiro = partes[0] || (temDecimal ? "0" : "");
        const decimal = partes.slice(1).join("").slice(0, 2);

        if (!finalizar) {
            return temDecimal ? `${inteiro}.${decimal}` : inteiro;
        }

        if (!inteiro && !decimal) {
            return "";
        }

        return `${inteiro || "0"}.${decimal.padEnd(2, "0")}`;
    };

    const formatarInteiro = (valor) => somenteDigitos(valor);

    const aplicarAtributos = (campo) => {
        const regra = regras[campo.dataset.validacao];

        if (!regra) {
            return;
        }

        Object.entries(regra).forEach(([atributo, valor]) => {
            campo.setAttribute(atributo, valor);
        });
    };

    const aplicarMascara = (campo, finalizar = false) => {
        switch (campo.dataset.validacao) {
            case "cpf":
                campo.value = formatarCpf(campo.value);
                break;
            case "telefone":
                campo.value = formatarTelefone(campo.value);
                break;
            case "dinheiro":
                campo.value = formatarDinheiro(campo.value, finalizar);
                break;
            case "inteiro":
                campo.value = formatarInteiro(campo.value);
                break;
            default:
                break;
        }
    };

    const configurarCampo = (campo) => {
        aplicarAtributos(campo);
        aplicarMascara(campo);
    };

    document.addEventListener("input", (event) => {
        const campo = event.target.closest("[data-validacao]");

        if (!campo) {
            return;
        }

        aplicarMascara(campo);
    });

    document.addEventListener("blur", (event) => {
        const campo = event.target.closest("[data-validacao='dinheiro']");

        if (!campo) {
            return;
        }

        aplicarMascara(campo, true);
    }, true);

    document.addEventListener("submit", (event) => {
        event.target
            .querySelectorAll("[data-validacao='dinheiro']")
            .forEach((campo) => aplicarMascara(campo, true));
    }, true);

    document.addEventListener("DOMContentLoaded", () => {
        document.querySelectorAll("[data-validacao]").forEach(configurarCampo);

        const observer = new MutationObserver((mutations) => {
            mutations.forEach((mutation) => {
                mutation.addedNodes.forEach((node) => {
                    if (!(node instanceof HTMLElement)) {
                        return;
                    }

                    if (node.matches("[data-validacao]")) {
                        configurarCampo(node);
                    }

                    node.querySelectorAll?.("[data-validacao]").forEach(configurarCampo);
                });
            });
        });

        observer.observe(document.body, { childList: true, subtree: true });
    });

    window.validacoesInovatech = {
        configurarCampo,
        aplicarMascara
    };
})();
