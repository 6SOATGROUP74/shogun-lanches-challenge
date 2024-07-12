ALTER TABLE db_soat.tb_pagamento ADD cod_pagamento varchar(255) NULL COMMENT 'Código do pagamento gerado pela adquirência';

ALTER TABLE db_soat.tb_pedido ADD cod_pedido varchar(255) NULL COMMENT 'Código único do pedido gerado pela adquirência';
ALTER TABLE db_soat.tb_pedido ADD cod_referencia_pedido varchar(255) NULL COMMENT 'Código de referencia do pedido';
