ALTER TABLE `ingresosgastos`.`ingresosgastos` DROP PRIMARY KEY;

DROP TABLE `ingresosgastos`.`ingresosgastos`;

CREATE TABLE `ingresosgastos`.`ingresosgastos` (
	`id` INTEGER UNSIGNED NOT NULL,
	`fecha` DATETIME,
	`tipo` VARCHAR(45) DEFAULT '' NOT NULL,
	`cantidad` VARCHAR(45) DEFAULT '' NOT NULL,
	`concepto` TEXT DEFAULT '' NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `ingresosgastos`.`ingresosgastos` ADD PRIMARY KEY (`id`);

