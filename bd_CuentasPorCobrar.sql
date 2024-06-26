USE [master]
GO
/****** Object:  Database [CuentasPorCobrar]    Script Date: 17/06/2024 23:14:46 ******/
CREATE DATABASE [CuentasPorCobrar]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CuentasPorCobrar', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\CuentasPorCobrar.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CuentasPorCobrar_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\CuentasPorCobrar_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [CuentasPorCobrar] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CuentasPorCobrar].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CuentasPorCobrar] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET ARITHABORT OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [CuentasPorCobrar] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CuentasPorCobrar] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CuentasPorCobrar] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET  ENABLE_BROKER 
GO
ALTER DATABASE [CuentasPorCobrar] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CuentasPorCobrar] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [CuentasPorCobrar] SET  MULTI_USER 
GO
ALTER DATABASE [CuentasPorCobrar] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CuentasPorCobrar] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CuentasPorCobrar] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CuentasPorCobrar] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CuentasPorCobrar] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CuentasPorCobrar] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [CuentasPorCobrar] SET QUERY_STORE = ON
GO
ALTER DATABASE [CuentasPorCobrar] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [CuentasPorCobrar]
GO
/****** Object:  User [usersql1]    Script Date: 17/06/2024 23:14:46 ******/
CREATE USER [usersql1] FOR LOGIN [usersql1] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [usersql]    Script Date: 17/06/2024 23:14:46 ******/
CREATE USER [usersql] WITHOUT LOGIN WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[CajaBanco]    Script Date: 17/06/2024 23:14:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CajaBanco](
	[CajaBancoID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [nvarchar](100) NULL,
	[Saldo] [decimal](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[CajaBancoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Canjes]    Script Date: 17/06/2024 23:14:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Canjes](
	[CanjeID] [int] IDENTITY(1,1) NOT NULL,
	[FacturaID] [int] NOT NULL,
	[Monto] [decimal](10, 2) NOT NULL,
	[Fecha] [date] NOT NULL,
	[Descripcion] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CanjeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cheques]    Script Date: 17/06/2024 23:14:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cheques](
	[chequeId] [int] IDENTITY(1,1) NOT NULL,
	[facturaId] [int] NOT NULL,
	[monto] [decimal](10, 2) NOT NULL,
	[fecha] [date] NOT NULL,
	[numeroCheque] [varchar](50) NOT NULL,
	[banco] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[chequeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clientes]    Script Date: 17/06/2024 23:14:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clientes](
	[ClienteID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [nvarchar](100) NULL,
	[Direccion] [nvarchar](100) NULL,
	[Telefono] [nvarchar](50) NULL,
	[Email] [nvarchar](100) NULL,
	[LineaCredito] [decimal](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[ClienteID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Facturas]    Script Date: 17/06/2024 23:14:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Facturas](
	[FacturaID] [int] IDENTITY(1,1) NOT NULL,
	[ClienteID] [int] NULL,
	[Fecha] [date] NULL,
	[Monto] [decimal](18, 2) NULL,
	[FormaPago] [nvarchar](50) NULL,
	[FechaVencimiento] [date] NULL,
	[Estado] [nvarchar](50) NULL,
	[cajaBancoId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[FacturaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pagos]    Script Date: 17/06/2024 23:14:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pagos](
	[PagoID] [int] IDENTITY(1,1) NOT NULL,
	[FacturaID] [int] NULL,
	[Monto] [decimal](18, 2) NULL,
	[Fecha] [date] NULL,
	[MedioPago] [nvarchar](50) NULL,
	[Detalles] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[PagoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transferencias]    Script Date: 17/06/2024 23:14:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transferencias](
	[TransferenciaID] [int] IDENTITY(1,1) NOT NULL,
	[FacturaID] [int] NOT NULL,
	[Monto] [decimal](10, 2) NOT NULL,
	[Fecha] [date] NOT NULL,
	[NumeroCuenta] [varchar](50) NOT NULL,
	[Banco] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TransferenciaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Facturas]  WITH CHECK ADD FOREIGN KEY([ClienteID])
REFERENCES [dbo].[Clientes] ([ClienteID])
GO
ALTER TABLE [dbo].[Facturas]  WITH CHECK ADD  CONSTRAINT [fk_facturas_cajaBanco] FOREIGN KEY([cajaBancoId])
REFERENCES [dbo].[CajaBanco] ([CajaBancoID])
GO
ALTER TABLE [dbo].[Facturas] CHECK CONSTRAINT [fk_facturas_cajaBanco]
GO
ALTER TABLE [dbo].[Pagos]  WITH CHECK ADD FOREIGN KEY([FacturaID])
REFERENCES [dbo].[Facturas] ([FacturaID])
GO
ALTER TABLE [dbo].[Transferencias]  WITH CHECK ADD  CONSTRAINT [FK_Transferencias_Facturas] FOREIGN KEY([FacturaID])
REFERENCES [dbo].[Facturas] ([FacturaID])
GO
ALTER TABLE [dbo].[Transferencias] CHECK CONSTRAINT [FK_Transferencias_Facturas]
GO
USE [master]
GO
ALTER DATABASE [CuentasPorCobrar] SET  READ_WRITE 
GO
